package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.*;
import com.intellij.openapi.vfs.ex.VirtualFileManagerAdapter;
import com.intellij.openapi.vfs.ex.VirtualFileManagerEx;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.impl.PsiVFSListener;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.util.messages.Topic;
import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.parser.ClassParser;
import com.jakutenshi.projects.umlplugin.parser.InterfaceParser;
import com.jakutenshi.projects.umlplugin.parser.EnumParser;
import com.jakutenshi.projects.umlplugin.parser.UMLEntityParser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class GenDiagramAction extends AnAction {
    private static Project project;
    private PsiManager psiManager;

    private VirtualFileAdapter virtualFilesCheckAdapter = new VirtualFileAdapter() {
        @Override
        public void beforeFileDeletion(@NotNull VirtualFileEvent event) {
            VirtualFile file = event.getFile();

        }

        @Override
        public void fileCreated(@NotNull VirtualFileEvent event) {
            VirtualFile file = event.getFile();

        }

        @Override
        public void fileMoved(@NotNull VirtualFileMoveEvent event) {
            VirtualFile newFile = event.getFile();

        }

        @Override
        public void contentsChanged(@NotNull VirtualFileEvent event) {
            VirtualFile file = event.getFile();
            if (isJavaFile(file)) {
                PsiJavaFileImpl javaFile = (PsiJavaFileImpl) psiManager.findFile(event.getFile());
                ArrayList<UMLEntity> entities = new ArrayList<>();
                parseJavaFile(javaFile.getChildren(), entities);
                DiagramContainer container = DiagramContainer.getInstance();
                for (UMLEntity entity : entities) {

                }
            }
        }

    };

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        project = anActionEvent.getProject();
        psiManager = PsiManager.getInstance(project);

        parseProject();

        //VirtualFileManager.getInstance().addVirtualFileListener(virtualFilesCheckAdapter);

    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);

    }

    private void parseProject() {
        DiagramContainer container = DiagramContainer.getInstance();
        container.clearContainer();
        ArrayList<UMLEntity> umlEntities = new ArrayList<>();
        parseForJavaFiles(project.getBaseDir().getChildren(), umlEntities);
        for (UMLEntity entity : umlEntities) {
            container.addUMLEntity(entity);
        }
        DiagramContainer.getInstance().notifyObservers();
    }

    private void parseForJavaFiles(VirtualFile[] virtualFiles, ArrayList<UMLEntity> umlEntities) {
        if (virtualFiles == null) return;
        PsiFile psiFile;

        for (VirtualFile virtualFile : virtualFiles) {
            psiFile = psiManager.findFile(virtualFile);
            //Find all Java files
            if (isJavaFile(virtualFile)) {
                parseJavaFile(psiFile.getChildren(), umlEntities);
            }
            else {
                parseForJavaFiles(virtualFile.getChildren(), umlEntities);
            }
        }

    }

    private void parseJavaFile(PsiElement[] elements, ArrayList<UMLEntity> umlEntities) {
        if (elements == null && elements.length == 0) {
            return;
        }
        UMLEntity entity;
        EnumParser enumParser = new EnumParser();
        InterfaceParser interfaceParser = new InterfaceParser();
        ClassParser classParser = new ClassParser();

        for (PsiElement element : elements) {
            if ((element instanceof PsiClass)
                    && !(element instanceof PsiAnonymousClass)
                    && !(element instanceof PsiTypeParameter)) {
                PsiClass psiClass = (PsiClass) element;
//парсим сущность
                if(psiClass.isInterface()){
                    entity = interfaceParser.parse(psiClass);
                } else if (psiClass.isEnum()) {
                    entity = enumParser.parse(psiClass);
                } else {
                    entity = classParser.parse(psiClass);
                }
//ищем внутренние сущности
                PsiClass[] psiInnerEntities = psiClass.getInnerClasses();
                for (PsiClass innerEntity : psiInnerEntities) {
                    entity.addInnerEntities(innerEntity.getQualifiedName());
                }
//добавляем в контейнер
                umlEntities.add(entity);
            }
            parseJavaFile(element.getChildren(), umlEntities);
        }
    }

    private boolean isJavaFile(VirtualFile file) {
        if (psiManager.findFile(file) instanceof PsiJavaFileImpl) {
            return true;
        } else {
            return false;
        }
    }

    public static Project getProject() {
        return project;
    }
}

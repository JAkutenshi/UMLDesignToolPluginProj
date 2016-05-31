package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.parser.ClassParser;
import com.jakutenshi.projects.umlplugin.parser.InterfaceParser;
import com.jakutenshi.projects.umlplugin.parser.EnumParser;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class GenDiagramAction extends AnAction {

    private static Project project;
    private PsiManager psiManager;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        project = anActionEvent.getProject();
        psiManager = PsiManager.getInstance(project);
        VirtualFile projectVirtualFile = project.getBaseDir();
        parseForJavaFiles(projectVirtualFile.getChildren());
        System.out.println();
        DiagramContainer container = DiagramContainer.getInstance();
        System.out.println(container.toString());

    }

    private void parseForJavaFiles(VirtualFile[] virtualFiles) {
        if (virtualFiles == null) return;
        PsiFile psiFile;

        for (VirtualFile virtualFile : virtualFiles) {
            psiFile = psiManager.findFile(virtualFile);
            //Find all Java files
            if (psiFile instanceof PsiJavaFileImpl) {
                parseJavaFile(psiFile.getChildren());
            }
            else {
                parseForJavaFiles(virtualFile.getChildren());
            }
        }

    }

    private void parseJavaFile(PsiElement[] elements) {
        if (elements == null) {
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
                DiagramContainer.getInstance().addUMLEntity(entity);
            }
            parseJavaFile(element.getChildren());
        }
    }

    public static Project getProject() {
        return project;
    }
}

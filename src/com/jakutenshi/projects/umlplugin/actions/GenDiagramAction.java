package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
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

    private Project project;
    private PsiManager psiManager;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        project = anActionEvent.getProject();
        psiManager = PsiManager.getInstance(project);
        VirtualFile projectVirtualFile = project.getBaseDir();
        parseForJavaFiles(projectVirtualFile.getChildren());
        //System.out.println(DiagramContainer.getInstance().toString());
    }

    private void parseForJavaFiles(VirtualFile[] virtualFiles) {
        if (virtualFiles == null) return;
        PsiFile psiFile;

        for (VirtualFile virtualFile : virtualFiles) {
            psiFile = psiManager.findFile(virtualFile);
            if (psiFile instanceof PsiJavaFileImpl) {
//Find all Java files
                parseJavaFile(psiFile.getChildren());
            } else {
                parseForJavaFiles(virtualFile.getChildren());
            }
        }

    }

    private void parseJavaFile(PsiElement[] elements) {
        if (elements == null) return;

        UMLEntity entity;
        Enum enumClass;
        EnumParser enumParser = new EnumParser();
        Interface anInterface;
        InterfaceParser interfaceParser = new InterfaceParser();
        Class aClass;
        ClassParser classParser = new ClassParser();

        for (PsiElement element : elements) {
            if (element instanceof PsiClassImpl) {
                PsiClassImpl psiClass = (PsiClassImpl) element;
                if(psiClass.isInterface()){
                    entity = (UMLEntity) interfaceParser.parse(psiClass);
                } else if (psiClass.isEnum()) {
                    entity = (UMLEntity) enumParser.parse(psiClass);
                } else {
                    entity = (UMLEntity) classParser.parse(psiClass);
                }
                DiagramContainer.getInstance().addUMLEntity(entity);
            }
            parseJavaFile(element.getChildren());
        }
    }
}

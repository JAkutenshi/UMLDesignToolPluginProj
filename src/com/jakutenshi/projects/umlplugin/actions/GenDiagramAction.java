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
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.parser.ParseEnum;

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
    }

    private void parseForJavaFiles(VirtualFile[] virtualFiles) {
        if (virtualFiles == null) return;
        PsiFile psiFile;

        for (VirtualFile virtualFile : virtualFiles) {
            psiFile = psiManager.findFile(virtualFile);
            if (psiFile instanceof PsiJavaFileImpl) {
                //Find all Java files
                System.out.println(virtualFile.getPath());
                parseJavaFile(psiFile.getChildren());
            } else {
                parseForJavaFiles(virtualFile.getChildren());
            }
        }

    }

    private void parseJavaFile(PsiElement[] elements) {
        if (elements == null) return;


        for (PsiElement element : elements) {
            if (element instanceof PsiClass) {
                PsiClassImpl psiClass = (PsiClassImpl) element;

                if(psiClass.isInterface()){
                    //ToDo
                } else if (psiClass.isEnum()) {
                    Enum enumClass = ParseEnum.parse(psiClass);
                    System.out.println("UML==============");
                    System.out.println(enumClass.toUML());
                    System.out.println("Code=============");
                    System.out.println(enumClass.toCode());
                }

            }

            parseJavaFile(element.getChildren());
        }
    }
}

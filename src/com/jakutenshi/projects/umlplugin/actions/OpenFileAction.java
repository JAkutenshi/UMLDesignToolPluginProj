package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.jakutenshi.projects.umlplugin.container.DiagramContainer;


/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class OpenFileAction extends AnAction {

    private Project project;
    private DiagramContainer diagram = new DiagramContainer();

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        VirtualFile virtualFile = project.getBaseDir();
        VirtualFile fileB = virtualFile.findFileByRelativePath("/src/com/jakutenshi/test/B.java");
        PsiJavaFileImpl javaFile = (PsiJavaFileImpl) PsiManager.getInstance(project).findFile(fileB);
        //System.out.println(javaFile.getText());
        PsiElement[] elements = javaFile.getChildren();

    }


}

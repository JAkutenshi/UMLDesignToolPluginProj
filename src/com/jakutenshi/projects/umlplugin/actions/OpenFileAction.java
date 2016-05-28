package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.lang.ASTNode;
import com.intellij.lang.FileASTNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.java.stubs.JavaClassElementType;
import com.intellij.psi.impl.java.stubs.JavaStubElementType;
import com.intellij.psi.impl.java.stubs.hierarchy.IndexTree;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.stubs.AbstractStubIndex;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubTree;
import com.jakutenshi.projects.umlplugin.container.DiagramContainer;

import java.util.List;

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
        virtualFile.findFileByRelativePath("/src/com/jakutenshi/test/B.java");
        PsiJavaFileImpl javaFile = (PsiJavaFileImpl) PsiManager.getInstance(project).findFile(virtualFile);
        System.out.println(javaFile.getText());
        PsiElement[] elements = javaFile.getChildren();
        javaFileEviscerate(javaFile.getChildren());
    }

    private void javaFileEviscerate(PsiElement[] elements) {
        if (elements == null) return;

        for (PsiElement element : elements) {
            if (element instanceof  PsiMethod) {
                System.out.println(element.getText());
            }
            javaFileEviscerate(element.getChildren());
        }
    }
}

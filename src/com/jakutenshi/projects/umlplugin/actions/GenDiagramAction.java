package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.java.stubs.JavaFieldStubElementType;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubTree;

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
                //ToDo
            } else {
                parseForJavaFiles(virtualFile.getChildren());
            }
        }

    }

    private void parseClassesViaStub(StubElement stubElement) {
        //ToDo
    }
}

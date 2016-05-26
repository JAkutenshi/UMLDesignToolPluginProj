package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.lang.Language;
import com.intellij.lang.StdLanguages;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VFileProperty;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.jakutenshi.projects.umlplugin.container.DiagramContainer;
import com.jakutenshi.projects.umlplugin.container.elements.ClassElement;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class OpenFileAction extends AnAction {

    private Project project;
    private DiagramContainer diagram = new DiagramContainer();

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        //ToDo
        project = anActionEvent.getProject();
        System.out.println(project.toString());

        VirtualFile projectVirtualFile = project.getBaseDir();
        System.out.println(project.toString());

        parseDir(projectVirtualFile);

        System.out.println("---------------");
        //System.out.println(PsiManager.getInstance(project).getProject().getName());
        //PsiManager.getInstance(project).findFile(VirtualFileManager.getInstance().)
        //PsiFile psiFile = fileViewProvider.getPsi(JavaLanguage.INSTANCE);
        //System.out.println(psiFile.toString());
        System.out.println(diagram.toString());

    }

    void parseDir(VirtualFile f) {
        if (f.isDirectory()) {
            for (VirtualFile child : f.getChildren()) {
                parseDir(child);
            }
        } else {
            System.out.println(f.getPath());
            PsiFile psiFile = PsiManager.getInstance(project).findFile(f);
            if (psiFile instanceof PsiJavaFile) {
                System.out.println("JAVA!");
                PsiJavaFile javaFile = (PsiJavaFile) psiFile;
                diagram.addUMLElement(new ClassElement(f.getPath(), javaFile.getName()));
            }
        }
    }
}

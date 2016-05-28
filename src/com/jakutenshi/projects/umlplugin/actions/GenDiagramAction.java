package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.*;
import com.intellij.psi.impl.java.stubs.JavaFieldStubElementType;
import com.intellij.psi.impl.java.stubs.JavaStubElementType;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubTree;
import com.jakutenshi.projects.umlplugin.container.elements.Field;
import com.jakutenshi.projects.umlplugin.container.elements.Method;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;

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
        Method method;
        Field field;

        for (PsiElement element : elements) {
            if (element instanceof PsiClass) {
                PsiClassImpl psiClass = (PsiClassImpl) element;

                if(psiClass.isInterface()){
                    System.out.println("!!====");
                }


                PsiIdentifier psiIdentifier = psiClass.getNameIdentifier();
                //System.out.println(psiIdentifier.getText());

                System.out.println("ClassName : " + psiClass.getName());
                System.out.println("Fields:");
                PsiField[] fields = psiClass.getFields();
                for (PsiField psiField : fields) {
                    field = ParseField.parseField(psiField);
                    System.out.println(field.toString());
                }
                System.out.println("Methods:");
                PsiMethod[] methods = psiClass.getMethods();
                for (PsiMethod psiMethod : methods) {
                    method = ParseMethod.createMethod(psiMethod);
                    System.out.println(method.toString());
                }
            }

            parseJavaFile(element.getChildren());
        }
    }
}

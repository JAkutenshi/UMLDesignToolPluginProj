package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.jakutenshi.projects.umlplugin.container.UMLElement;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public interface Parser {
    UMLElement parse(PsiElement psiElement);
}

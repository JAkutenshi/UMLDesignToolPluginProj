package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiTypeParameter;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumField;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.TypeParameter;

/**
 * Created by JAkutenshi on 30.05.2016.
 */
public class TypeParameterParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        PsiTypeParameter psiTypeParameter = (PsiTypeParameter) psiElement;
        return new TypeParameter(psiTypeParameter.getName());
    }
}

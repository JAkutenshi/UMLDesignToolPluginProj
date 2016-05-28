package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiParameter;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Parameter;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParameterParser implements Parser{
    @Override
    public UMLElement parse(PsiElement psiElement) {
        PsiParameter psiField = (PsiParameter) psiElement;
        Parameter parameter = new Parameter();
//имя
        parameter.setName(psiField.getName());
//тип
        parameter.setType(psiField.getType().getPresentableText());
//ключевые слова
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiField.getModifierList());
        parameter.setKeywords(modifierParser.getParseKeywords());

        return parameter;
    }
}

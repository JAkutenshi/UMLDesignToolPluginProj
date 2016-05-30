package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.*;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Parameter;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParameterParser implements Parser{
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Parameter parameter = new Parameter();

        PsiParameter psiParameter = (PsiParameter) psiElement;
//имя
        parameter.setName(psiParameter.getName());
//тип
        parameter.setType(psiParameter.getType().getPresentableText());
//ключевые слова
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiParameter.getModifierList());
        parameter.setKeywords(modifierParser.getParseKeywords());

        return parameter;
    }
}

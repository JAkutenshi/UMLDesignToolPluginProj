package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Parameter;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class MethodParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Method method = new Method();
        PsiMethod psiMethod = (PsiMethod) psiElement;
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiMethod.getModifierList());
        method.setScope(modifierParser.getParseScope());
        method.setKeywords(modifierParser.getParseKeywords());
//имя
        method.setName(psiMethod.getName());
//возвращаемый тип
        if (psiMethod.getReturnType() != null) {
            method.setReturnType(psiMethod.getReturnType().getPresentableText());
        } else {
            method.setReturnType(null);
        }

//параметры
        PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        ParameterParser parameterParser = new ParameterParser();
        for (PsiParameter psiParameter : parameters) {
            method.addParameter((Parameter) parameterParser.parse(psiParameter));
        }

        return method;
    }
}

package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Parameter;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Signature;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class SignatureParser implements Parser{

    @Override
    public UMLElement parse(PsiElement psiElement) {
        Signature signature = new Signature();
        PsiMethod psiMethod = (PsiMethod) psiElement;
        //имя
        signature.setName(psiMethod.getName());
        //возвращаемый тип
        signature.setReturnType(psiMethod.getReturnType().getPresentableText());
        //параметры
        PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
        ParameterParser parameterParser = new ParameterParser();
        for (PsiParameter psiParameter : parameters) {
            signature.addParameter((Parameter) parameterParser.parse(psiParameter));
        }
        //ключевые слова
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiMethod.getModifierList());
        signature.setKeywords(modifierParser.getParseKeywords());

        return signature;
    }
}

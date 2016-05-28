package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Signature;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class InterfaceParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Interface anInterface = new Interface();
        PsiClass psiClass = (PsiClass) psiElement;
//имя
        anInterface.setName(psiClass.getName());
//пакет
        anInterface.setPackagePath(psiClass.getQualifiedName());
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        anInterface.setScope(modifierParser.getParseScope());
        anInterface.setKeywords(modifierParser.getParseKeywords());
//сигнатуры
        PsiMethod[] psiMethods = psiClass.getMethods();
        SignatureParser signatureParser = new SignatureParser();
        for (PsiMethod psiMethod : psiMethods) {
            anInterface.addSignature((Signature) signatureParser.parse(psiMethod));
        }
//предок
        anInterface.setExtend(psiClass.getExtendsList().toString());
        return anInterface;
    }
}

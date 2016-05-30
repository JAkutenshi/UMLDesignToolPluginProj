package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiTypeParameter;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.TypeParameter;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class InterfaceParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Interface anInterface = new Interface();
        PsiClass psiClass = (PsiClass) psiElement;
//пакет
        anInterface.setPackagePath(psiClass.getQualifiedName());
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        anInterface.setScope(modifierParser.getParseScope());
        anInterface.setKeywords(modifierParser.getParseKeywords());
//имя
        anInterface.setName(psiClass.getName());
//генерик-типы
        PsiTypeParameter[] psiTypeParameters = psiClass.getTypeParameters();
        if (psiTypeParameters != null) {
            TypeParameterParser typeParameterParser = new TypeParameterParser();
            for (PsiTypeParameter psiTypeParameter : psiTypeParameters) {
                anInterface.addTypeParameter((TypeParameter) typeParameterParser.parse(psiTypeParameter));
            }
        }
//сигнатуры
        PsiMethod[] psiMethods = psiClass.getMethods();
        MethodParser signatureParser = new MethodParser();
        Method method;
        for (PsiMethod psiMethod : psiMethods) {
            method = (Method) signatureParser.parse(psiMethod);
            method.setIntarfaceSignature(true);
            anInterface.addSignature(method);
        }
//предок
        anInterface.setExtend(psiClass.getExtendsList().toString());
        return anInterface;
    }
}

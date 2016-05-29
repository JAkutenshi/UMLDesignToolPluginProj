package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Field;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ClassParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Class aClass = new Class();
        PsiClassImpl psiClass = (PsiClassImpl) psiElement;

//!generic test
        System.out.println(psiClass.getQualifiedName());

        PsiTypeParameter[] psiTypeParameters = psiClass.getTypeParameters();
        if (psiTypeParameters != null) {
            for (PsiTypeParameter psiTypeParameter : psiTypeParameters) {
                System.out.println(psiTypeParameter.getName());
                PsiClassType[] c =  psiTypeParameter.getExtendsListTypes();
                if (c != null) {
                    for (PsiClassType t : c) {
                        System.out.println('\t' + t.getCanonicalText());

                    }
                }
            }
        }

//!generic test

//имя
        aClass.setName(psiClass.getName());
//Является ли исключением
//TODO EXCEPTION
//пакет
        aClass.setPackagePath(psiClass.getQualifiedName());
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        aClass.setScope(modifierParser.getParseScope());
        aClass.setKeywords(modifierParser.getParseKeywords());
//поля
        FieldParser fieldParser = new FieldParser();
        PsiField[] fields = psiClass.getFields();
        for (PsiField psiField : fields) {
            aClass.addField((Field) fieldParser.parse(psiField));
        }
//методы
        MethodParser methodParser = new MethodParser();
        PsiMethod[] psiMethods = psiClass.getMethods();
        for (PsiMethod psiMethod : psiMethods) {
            aClass.addMethod((Method) methodParser.parse(psiMethod));
        }
        return aClass;
    }
}

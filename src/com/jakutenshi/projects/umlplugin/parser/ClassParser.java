package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Field;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.TypeParameter;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ClassParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Class aClass = new Class();
        PsiClassImpl psiClass = (PsiClassImpl) psiElement;

//пакет
        aClass.setPackagePath(psiClass.getQualifiedName());
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        aClass.setScope(modifierParser.getParseScope());
        aClass.setKeywords(modifierParser.getParseKeywords());
//имя
        aClass.setName(psiClass.getName());
//генерик-типы
        PsiTypeParameter[] psiTypeParameters = psiClass.getTypeParameters();
        if (psiTypeParameters != null) {
            TypeParameterParser typeParameterParser = new TypeParameterParser();
            for (PsiTypeParameter psiTypeParameter : psiTypeParameters) {
                aClass.addTypeParameter((TypeParameter) typeParameterParser.parse(psiTypeParameter));
            }
        }
//Является ли исключением
//TODO EXCEPTION
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

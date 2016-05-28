package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
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
        PsiClass psiClass = (PsiClass) psiElement;
        //имя
        aClass.setName(psiClass.getName());
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

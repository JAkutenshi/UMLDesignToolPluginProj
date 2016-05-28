package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumField;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class EnumParser implements Parser{
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Enum enumClass = new Enum();
        PsiClass psiClass = (PsiClass) psiElement;
        //имя
        enumClass.setName(psiClass.getName());
        //пакет
        enumClass.setPackagePath(psiClass.getQualifiedName());
        //поля перечисления
        PsiField[] fields = psiClass.getAllFields();
        EnumFieldParser enumFieldParser = new EnumFieldParser();
        for (PsiField field : fields) {
            if (field instanceof PsiEnumConstant) {
                enumClass.addEnumField((EnumField) enumFieldParser.parse(field));
            }
        }
        //область видимости
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        enumClass.setScope(modifierParser.getParseScope());
        enumClass.setKeywords(modifierParser.getParseKeywords());

        return enumClass;
    }
}

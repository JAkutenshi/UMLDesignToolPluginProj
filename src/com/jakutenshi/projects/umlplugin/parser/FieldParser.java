package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Field;

/**
 * Created by JAkutenshi on 29.05.2016.
 */
public class FieldParser implements Parser {
    @Override
    public UMLElement parse(PsiElement psiElement) {
        Field field = new Field();
        PsiField psiField = (PsiField) psiElement;
//моификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiField.getModifierList());
        field.setScope(modifierParser.getParseScope());
        field.setKeywords(modifierParser.getParseKeywords());
//тип
        field.setType(psiField.getType().getPresentableText());
//имя
        field.setName(psiField.getName());

        return field;
    }
}

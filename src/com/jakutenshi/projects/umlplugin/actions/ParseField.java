package com.jakutenshi.projects.umlplugin.actions;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifierList;
import com.jakutenshi.projects.umlplugin.container.elements.Field;
import com.jakutenshi.projects.umlplugin.container.elements.Scope;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParseField {
    public static Field parseField(PsiField psiField) {
        Field newField = new Field();
        //модификаторы
        modifiersAnalyse(newField, psiField);
        //имя
        newField.setName(psiField.getName());
        //тип
        newField.setType(psiField.getType().getPresentableText());
        //ToDo Начальное значение

        return newField;
    }

    private static void modifiersAnalyse(Field field, PsiField psiField) {
        PsiModifierList psiModifierList = psiField.getModifierList();
        PsiElement[] modifiers =  psiModifierList.getChildren();
        for (PsiElement modifier : modifiers) {
            switch (modifier.getText()) {
                case "public" :
                    field.setScope(Scope.PUBLIC);
                    break;
                case "private" :
                    field.setScope(Scope.PRIVATE);
                    break;
                case "package" :
                    field.setScope(Scope.PACKAGE);
                    break;
                case "protected" :
                    field.setScope(Scope.PROTECTED);
                    break;
                case "final" :
                    field.setFinal(true);
                    break;
                case "static" :
                    field.setStatic(true);
                    break;
                default:
                    break;
            }
        }
    }
}

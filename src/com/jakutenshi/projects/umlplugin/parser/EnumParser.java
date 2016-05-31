package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class EnumParser extends UMLEntityParser {
    @Override
    public UMLEntity parse(PsiClass psiClass) {
        Enum enumClass = new Enum();
//имя
        enumClass.setName(psiClass.getName());
//пакет
        enumClass.setPackagePath(psiClass.getQualifiedName());
//поля перечисления
        PsiField[] psiFields = psiClass.getAllFields();
        for (PsiField psiField : psiFields) {
            if (psiField instanceof PsiEnumConstant) {
                enumClass.addEnumField(parseEnumConstant((PsiEnumConstant) psiField));
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

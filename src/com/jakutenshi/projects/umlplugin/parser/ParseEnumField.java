package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiField;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumField;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParseEnumField {
    public static EnumField parse(PsiField psiField) {
        EnumField enumField = new EnumField();
        //имя
        enumField.setName(psiField.getName());
        return enumField;
    }

}

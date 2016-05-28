package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.jakutenshi.projects.umlplugin.container.entities.Enum;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumField;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParseEnum {
    public static Enum parse(PsiClass psiClass) {
        Enum enumClass = new Enum();
        //имя
        enumClass.setName(psiClass.getName());
        //поля перечисления
        PsiField[] fields = psiClass.getAllFields();
        for (PsiField field : fields) {
            if (field instanceof PsiEnumConstant) {
                enumClass.addEnumField(ParseEnumField.parse(field));
            }
        }
        return enumClass;
    }
}

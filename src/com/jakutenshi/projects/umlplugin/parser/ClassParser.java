package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.*;
import com.jakutenshi.projects.umlplugin.container.entities.Class;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;


/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ClassParser extends UMLEntityParser {
    @Override
    public UMLEntity parse(PsiClass psiClass){
        Class aClass = new Class();
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
            for (PsiTypeParameter psiTypeParameter : psiTypeParameters) {
                aClass.addTypeParameter(parseTypeParameter(psiTypeParameter));
            }
        }
//поля
        PsiField[] fields = psiClass.getFields();
        for (PsiField psiField : fields) {
            aClass.addField(parseField(psiField));
        }
//если класс является службой
        if (aClass.getFields().size() == 0) {
            aClass.setUtility(true);
        }
//методы
        PsiMethod[] psiMethods = psiClass.getMethods();
        for (PsiMethod psiMethod : psiMethods) {
            aClass.addMethod(parseMethod(psiMethod));
        }
//является ли наследником
        aClass.setExtendsClass(parseExtendsEntity(psiClass));
//список реализующих интерфейсов
        aClass.setImplementInterfaces(parseImplementInterfases(psiClass));

        return aClass;
    }
}

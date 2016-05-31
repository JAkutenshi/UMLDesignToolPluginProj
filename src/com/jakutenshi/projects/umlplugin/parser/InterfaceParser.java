package com.jakutenshi.projects.umlplugin.parser;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiTypeParameter;
import com.jakutenshi.projects.umlplugin.container.entities.Interface;
import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class InterfaceParser extends UMLEntityParser {
    @Override
    public UMLEntity parse(PsiClass psiClass) {
        Interface anInterface = new Interface();
//пакет
        anInterface.setPackagePath(psiClass.getQualifiedName());
//модификаторы
        ModifierParser modifierParser = new ModifierParser();
        modifierParser.parse(psiClass.getModifierList());
        anInterface.setScope(modifierParser.getParseScope());
        anInterface.setKeywords(modifierParser.getParseKeywords());
//имя
        anInterface.setName(psiClass.getName());
//генерик-типы
        PsiTypeParameter[] psiTypeParameters = psiClass.getTypeParameters();
        if (psiTypeParameters != null) {
            for (PsiTypeParameter psiTypeParameter : psiTypeParameters) {
                anInterface.addTypeParameter(parseTypeParameter(psiTypeParameter));
            }
        }
//сигнатуры
        PsiMethod[] psiMethods = psiClass.getMethods();
        Method method;
        for (PsiMethod psiMethod : psiMethods) {
            method = parseMethod(psiMethod);
            method.setIntarfaceSignature(true);
            anInterface.addSignature(method);
        }
//предок
        anInterface.setExtendedInterface(parseExtendsEntity(psiClass));
        return anInterface;
    }
}

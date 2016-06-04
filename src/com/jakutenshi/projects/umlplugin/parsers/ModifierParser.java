package com.jakutenshi.projects.umlplugin.parsers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiKeyword;
import com.intellij.psi.PsiModifierList;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Scope;

import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ModifierParser {
    private Scope parseScope = Scope.PACKAGE;
    private HashSet<Keyword> parseKeywords;

    public void parse(PsiModifierList psiModifierList) {
        if (psiModifierList == null) { return; }

        parseKeywords = new HashSet<>();
        PsiElement[] psiElements = psiModifierList.getChildren();
        for (PsiElement psiElement : psiElements) {
            if (psiElement instanceof PsiKeyword) {
                switch (psiElement.getText()) {
                    case "abstract" :
                        parseKeywords.add(Keyword.ABSTRACT);
                        break;
                    case "final" :
                        parseKeywords.add(Keyword.FINAL);
                        break;
                    case "static" :
                        parseKeywords.add(Keyword.STATIC);
                        break;
                    case "public" :
                        parseScope = Scope.PUBLIC;
                        break;
                    case "protected" :
                        parseScope = Scope.PROTECTED;
                        break;
                    case "private" :
                        parseScope = Scope.PRIVATE;
                        break;
                }
            }
        }
    }

    public ModifierParser() {
    }

    public Scope getParseScope() {
        return parseScope;
    }

    public HashSet<Keyword> getParseKeywords() {
        return parseKeywords;
    }
}

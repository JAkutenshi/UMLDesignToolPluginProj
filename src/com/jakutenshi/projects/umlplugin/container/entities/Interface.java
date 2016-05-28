package com.jakutenshi.projects.umlplugin.container.entities;

import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Signature;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Interface extends UMLEntity {
    private ArrayList<Signature> signatures = new ArrayList<>();

    @Override
    public String titleToUML() {
        return "<<interface>> " + getName();
    }

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(titleToUML())
                .append("\n---------------------\n");
        for (Signature signature : signatures) {
            builder.append(signature.toUML())
                    .append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toCode() {
        //TODO
        return null;
    }

    public Interface(String name, String packagePath, HashSet<Keyword> keywords, ArrayList<Signature> signatures) {
        super(name, packagePath, keywords);
        this.signatures = signatures;
    }

    public Interface(String name, String packagePath, ArrayList<Signature> signatures) {
        super(name, packagePath);
        this.signatures = signatures;
    }

    public Interface(ArrayList<Signature> signatures) {
        this.signatures = signatures;
    }

    public Interface(String name, String packagePath, HashSet<Keyword> keywords) {
        super(name, packagePath, keywords);
    }

    public Interface(String name, String packagePath) {
        super(name, packagePath);
    }

    public Interface() {
    }

    public ArrayList<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<Signature> signatures) {
        this.signatures = signatures;
    }

    public void addSignature(Signature signature) {
        signatures.add(signature);
    }

}

package com.jakutenshi.projects.umlplugin.container.entities;

import com.jakutenshi.projects.umlplugin.container.entities.attributes.Scope;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Signature;


import java.util.ArrayList;

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

    public Interface(String name, String packagePath, Scope scope) {
        super(name, packagePath, scope);
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

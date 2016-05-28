package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Method extends Signature {
    private Scope scope = Scope.PACKAGE;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toUML())
                .append(" ")
                .append(super.toUML());

        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toCode())
                .append(" ")
                .append(super.toCode());
        int semicolon = builder.length() - 1;
        builder.replace(semicolon, semicolon, " {\n\n}");
        return builder.toString();
    }

    public Method(String returnType, String name, ArrayList<Parameter> parameters, HashSet<Keyword> keywords, Scope scope) {
        super(returnType, name, parameters, keywords);
        this.scope = scope;
    }

    public Method(String returnType, String name, Scope scope) {
        super(returnType, name);
        this.scope = scope;
    }

    public Method(Scope scope) {
        this.scope = scope;
    }

    public Method(String returnType, String name, ArrayList<Parameter> parameters, HashSet<Keyword> keywords) {
        super(returnType, name, parameters, keywords);
    }

    public Method(String returnType, String name) {
        super(returnType, name);
    }

    public Method() {
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}

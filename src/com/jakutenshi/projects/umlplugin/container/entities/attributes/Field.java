package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Field extends Parameter {
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
                .append(" ");
        if (getKeywords().contains(Keyword.STATIC))
            builder.append("static ");
        builder.append(super.toCode());
        return builder.toString();
    }

    public Field(String name, HashSet<Keyword> keywords, String type, Scope scope) {
        super(name, keywords, type);
        this.scope = scope;
    }

    public Field(String name, String type, Scope scope) {
        super(name, type);
        this.scope = scope;
    }

    public Field(HashSet<Keyword> keywords, String type, Scope scope) {
        super(keywords, type);
        this.scope = scope;
    }

    public Field(Scope scope) {
        this.scope = scope;
    }

    public Field(String name, HashSet<Keyword> keywords, String type) {
        super(name, keywords, type);
    }

    public Field(String name, String type) {
        super(name, type);
    }

    public Field(HashSet<Keyword> keywords, String type) {
        super(keywords, type);
    }

    public Field() {
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}

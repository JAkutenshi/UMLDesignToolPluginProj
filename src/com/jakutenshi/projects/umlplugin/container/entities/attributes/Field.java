package com.jakutenshi.projects.umlplugin.container.entities.attributes;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Field extends Parameter {
    private Scope scope = Scope.PACKAGE;
    private boolean isStatic = false;

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
        if (isStatic)
            builder.append("static ");
        builder.append(super.toCode());
        return builder.toString();
    }

    public Field(String name, String type, boolean isFinal, Scope scope, boolean isStatic) {
        super(name, type, isFinal);
        this.scope = scope;
        this.isStatic = isStatic;
    }

    public Field(String type, boolean isFinal, Scope scope, boolean isStatic) {
        super(type, isFinal);
        this.scope = scope;
        this.isStatic = isStatic;
    }

    public Field(String name, String type, Scope scope, boolean isStatic) {
        super(name, type);
        this.scope = scope;
        this.isStatic = isStatic;
    }

    public Field(String name, Scope scope, boolean isStatic) {
        super(name);
        this.scope = scope;
        this.isStatic = isStatic;
    }

    public Field(Scope scope, boolean isStatic) {
        this.scope = scope;
        this.isStatic = isStatic;
    }

    public Field(String name, String type, boolean isFinal, Scope scope) {
        super(name, type, isFinal);
        this.scope = scope;
    }

    public Field(String type, boolean isFinal, Scope scope) {
        super(type, isFinal);
        this.scope = scope;
    }

    public Field(String name, String type, Scope scope) {
        super(name, type);
        this.scope = scope;
    }

    public Field(String name, Scope scope) {
        super(name);
        this.scope = scope;
    }

    public Field(Scope scope) {
        this.scope = scope;
    }

    public Field(String name, String type, boolean isFinal) {
        super(name, type, isFinal);
    }

    public Field(String type, boolean isFinal) {
        super(type, isFinal);
    }

    public Field(String name, String type) {
        super(name, type);
    }

    public Field(String name) {
        super(name);
    }

    public Field() {
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}

package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class Field {
    private Scope scope = Scope.PACKAGE;
    private String name;
    private String type;
    private String startValue = null;
    private boolean isFinal = false;
    private boolean isStatic = false;

    public Field() {
        name = "noname";
        type = "notype";
    }

    public Field(Scope scope, String name, String type, String startValue, boolean isFinal, boolean isStatic) {
        this.scope = scope;
        this.name = name;
        this.type = type;
        this.startValue = startValue;
        this.isFinal = isFinal;
        this.isStatic = isStatic;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toString())
                .append(" ")
                .append(name)
                .append(" : ")
                .append(type);
        if (startValue != null) {
            builder.append(" = ")
                    .append(startValue);
        }
        if (isFinal) {
            builder.append(" {readOnly}");
        }
        return builder.toString();
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}

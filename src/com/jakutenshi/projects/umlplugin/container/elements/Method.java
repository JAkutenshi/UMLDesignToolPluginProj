package com.jakutenshi.projects.umlplugin.container.elements;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class Method {
    private Scope scope = Scope.PACKAGE;
    private String name;
    private ArrayList<Parameter> parameters;
    private String returnType;
    private boolean isStatic = false;
    private boolean isFinal = false;

    public Method(Scope scope, String name, String returnType, ArrayList<Parameter> parameters, boolean isFinal, boolean isStatic) {
        this.scope = scope;
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Method(Scope scope, String name, String returnType, boolean isFinal, boolean isStatic) {
        this.scope = scope;
        this.name = name;
        this.returnType = returnType;
    }

    public Method() {
        name = "noname";
        returnType = "notype";
    }

    public Scope getScope() {
        return scope;
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

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toString())
                .append(" ")
                .append(name)
                .append(" (");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toString());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(") : ")
                .append(returnType);
        if (isFinal) {
            builder.append(" {readOnly}");
        }
        return builder.toString();
    }
}

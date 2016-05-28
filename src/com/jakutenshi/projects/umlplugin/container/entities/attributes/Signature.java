package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Generatable;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Signature extends UMLElement implements Generatable {
    private String returnType;
    private String name;
    private ArrayList<Parameter> parameters = new ArrayList<>();
    private boolean isAbstract = false;
    private boolean isFinal = false;
    private boolean isStatic = false;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType)
                .append(" ")
                .append(name)
                .append("(");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toCode());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(");");
        if(isFinal)
            builder.append(" {readOnly}");
        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        if (isAbstract)
            builder.append("abstract ");
        if (isFinal)
            builder.append("final ");
        if (isStatic)
            builder.append("static ");
        builder.append(returnType)
                .append(" ")
                .append(name)
                .append("(");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toCode());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(");");
        return builder.toString();
    }

    public Signature(String returnType, String name, ArrayList<Parameter> parameters) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
    }

    public Signature(String returnType, String name) {
        this.returnType = returnType;
        this.name = name;
    }

    public Signature() {
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
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

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
        if (isFinal && isAbstract)
            isFinal = false;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
        if (isAbstract && isFinal)
            isAbstract = false;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}

package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Generatable;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Signature extends UMLElement implements Generatable {
    private String returnType;
    private String name;
    private ArrayList<Parameter> parameters = new ArrayList<>();
    private HashSet<Keyword> keywords;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" (");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toCode());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");
//случай конструктора
        if (returnType != null) {
            builder.append(" : ")
                    .append(getReturnType());
        }
        if(keywords.contains(Keyword.FINAL))
            builder.append(" {readOnly}");
        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        if (keywords.contains(Keyword.ABSTRACT))
            builder.append("abstract ");
        if (keywords.contains(Keyword.FINAL))
            builder.append("final ");
        if (keywords.contains(Keyword.STATIC))
            builder.append("static ");
//случай конструктора
        if (returnType != null) {
            builder.append(returnType)
                    .append(" ");
        }
            builder.append(name)
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

    public Signature(String returnType, String name, ArrayList<Parameter> parameters, HashSet<Keyword> keywords) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.keywords = keywords;
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

    public HashSet<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(HashSet<Keyword> keywords) {
        this.keywords = keywords;
    }
}

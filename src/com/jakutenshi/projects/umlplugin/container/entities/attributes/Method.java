package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import com.jakutenshi.projects.umlplugin.container.entities.Generatable;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Method implements Generatable {
    private Scope scope = Scope.PACKAGE;
    private HashSet<Keyword> keywords = new HashSet<>();
    private ArrayList<TypeParameter> typeParameters = new ArrayList<>();
    private String returnType;
    private String name;
    private ArrayList<Parameter> parameters = new ArrayList<>();
    private boolean isIntarfaceSignature = false;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
//область видимости
        if (!isIntarfaceSignature) {
            builder.append(scope.toUML())
                    .append(" ");
        }
//имя
        builder.append(name);
//генерик типы, если есть
        buildtypeParameters(builder);
//параметры
        builder.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toUML());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");
//возвращаемый тип, если не коснтруктор
        if (returnType != null) {
            builder.append(" : ")
                    .append(returnType);
        }
//если не интерфейс и final, то readOnly уточнение
        if (!isIntarfaceSignature && keywords.contains(Keyword.FINAL)) {
            builder.append(" {readOnly}");
        }
        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        if (!isIntarfaceSignature) {
//область видимости
            builder.append(scope.toCode())
                    .append(" ");
//ключевые слова final abstract static
            for (Keyword keyword : keywords) {
                builder.append(keyword.toCode())
                        .append(" ");
            }
        }
//генерики, если есть
        buildtypeParameters(builder);
//возвращаемый тип, если не конструктор
        if (returnType != null) {
            builder.append(returnType)
                    .append(" ");
        }

//имя
        builder.append(name)
//параметры
                .append("(");
        for (int i = 0; i < parameters.size(); i++) {
            builder.append(parameters.get(i).toCode());
            if (i != parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");
        if (isIntarfaceSignature) {
//для сигнатуры интерфейса
            builder.append(";");
        } else {
//для метода класса
            builder.append(" {\n\t\\\\ToDo\n}");
        }
        return builder.toString();
    }

    private void buildtypeParameters(StringBuilder builder) {
        if (typeParameters != null && typeParameters.size() != 0) {
            builder.append("<");
            for (int i = 0; i < typeParameters.size(); i++) {
                builder.append(typeParameters.get(i).getName());
                if (i != typeParameters.size() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("> ");
        }
    }

    public Method() {
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

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public ArrayList<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(ArrayList<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }

    public void addTypeParameter(TypeParameter typeParameter) {
        typeParameters.add(typeParameter);
    }

    public boolean isIntarfaceSignature() {
        return isIntarfaceSignature;
    }

    public void setIntarfaceSignature(boolean intarfaceSignature) {
        isIntarfaceSignature = intarfaceSignature;
    }
}

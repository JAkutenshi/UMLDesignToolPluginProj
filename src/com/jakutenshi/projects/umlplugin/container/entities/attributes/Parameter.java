package com.jakutenshi.projects.umlplugin.container.entities.attributes;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Parameter extends EnumField {
    private String type;
    private boolean isFinal = false;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toUML())
                .append(" : ")
                .append(type);
        if(isFinal)
            builder.append(" {readOnly}");
        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        if (isFinal)
            builder.append("final ");
        builder.append(type)
                .append(" ")
                .append(super.toUML());
        return builder.toString();
    }

    public Parameter(String name, String type, boolean isFinal) {
        super(name);
        this.type = type;
        this.isFinal = isFinal;
    }

    public Parameter(String type, boolean isFinal) {
        this.type = type;
        this.isFinal = isFinal;
    }

    public Parameter(String name, String type) {
        super(name);
        this.type = type;
    }

    public Parameter(String name) {
        super(name);
    }

    public Parameter() {
    }

}

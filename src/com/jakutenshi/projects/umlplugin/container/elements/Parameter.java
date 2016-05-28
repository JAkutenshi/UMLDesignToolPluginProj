package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public class Parameter {
    private String name;
    private String type;
    private boolean isFinal = false;

    public Parameter() {
        name = "noname";
        type = "notype";
    }

    public Parameter(String name, String type, boolean isFinal) {
        this.name = name;
        this.type = type;
    }

    public Parameter(String name, String type) {
        this.name = name;
        this.type = type;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" : ")
                .append(type);
        if (isFinal) {
            builder.append(" {readOnly}");
        }
        return builder.toString();
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

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}

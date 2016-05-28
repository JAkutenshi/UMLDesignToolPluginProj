package com.jakutenshi.projects.umlplugin.container.elements;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class Method {
    private Modifier modifier;
    private String methodName;
    private ArrayList<Attribute> attributes;
    private String returnType;
    private Property property;

    public Method(Modifier modifier, String methodName, String returnType, Property property, ArrayList<Attribute> attributes) {
        this.modifier = modifier;
        this.methodName = methodName;
        this.returnType = returnType;
        this.property = property;
        this.attributes = attributes;
    }

    public Method(Modifier modifier, String methodName, Property property, String returnType) {
        this.modifier = modifier;
        this.methodName = methodName;
        this.property = property;
        this.returnType = returnType;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(modifier.toString())
                .append(" ")
                .append(methodName)
                .append(" (");
        for (Attribute attribute : attributes) {
            builder.append(attribute.toString())
                    .append(", ");
        }
        builder.append(") : ")
                .append(returnType)
                .append(" ")
                .append(property.toString());
        return builder.toString();
    }
}

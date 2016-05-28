package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public class Attribute {
    String name;
    String type;
    String defaultValue;
    Property property = Property.NONE;

    public Attribute(String name, String type, String defaultValue, Property property) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        this.property = property;
    }

    public Attribute(String name, String type, Property property) {
        this.name = name;
        this.type = type;
        this.property = property;
    }

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" : ")
                .append(type);
        if (defaultValue != null) {
            builder.append(" = ")
                    .append(defaultValue);
        }
        builder.append(property.toString());
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}

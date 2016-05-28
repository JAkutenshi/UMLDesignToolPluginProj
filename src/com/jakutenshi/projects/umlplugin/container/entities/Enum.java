package com.jakutenshi.projects.umlplugin.container.entities;


import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumField;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Scope;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Enum extends UMLEntity {
    private ArrayList<EnumField> enumFields = new ArrayList<>();

    @Override
    public String titleToUML() {
        return "<<enumeration>> " + getName();
    }

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(titleToUML())
                .append("\n---------------------\n");
        for (EnumField enumField : enumFields) {
            builder.append(enumField.toUML())
                    .append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(getScope().toCode())
                .append(" enum ")
                .append(getName())
                .append(" {\n");
        for (int i = 0; i < enumFields.size(); i++) {
            builder.append(enumFields.get(i).toCode());
            if (i != enumFields.size() - 1) {
                builder.append(",\n");
            }
        }
        builder.append("\n}");
        return builder.toString();
    }



    public Enum(String name, String packagePath, Scope scope) {
        super(name, packagePath, scope);
    }

    public Enum(String name, String packagePath) {
        super(name, packagePath);
    }

    public Enum() {
    }

    public ArrayList<EnumField> getEnumFields() {
        return enumFields;
    }

    public void setEnumFields(ArrayList<EnumField> enumFields) {
        this.enumFields = enumFields;
    }

    public void addEnumField(EnumField enumField) {
        enumFields.add(enumField);
    }

}

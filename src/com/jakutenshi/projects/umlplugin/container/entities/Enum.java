package com.jakutenshi.projects.umlplugin.container.entities;


import com.jakutenshi.projects.umlplugin.container.entities.attributes.EnumConstant;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Enum extends UMLEntity {
    private ArrayList<EnumConstant> enumConstants = new ArrayList<>();

    @Override
    public String titleToUML() {
        return "<<enumeration>> " + getName();
    }

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(titleToUML())
                .append("\n---------------------\n");
        for (EnumConstant enumConstant : enumConstants) {
            builder.append(enumConstant.toUML())
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
        for (int i = 0; i < enumConstants.size(); i++) {
            builder.append(enumConstants.get(i).toCode());
            if (i != enumConstants.size() - 1) {
                builder.append(",\n");
            }
        }
        builder.append("\n}");
        return builder.toString();
    }

    public Enum(String name, String packagePath, HashSet<Keyword> keywords, ArrayList<EnumConstant> enumConstants) {
        super(name, packagePath, keywords);
        this.enumConstants = enumConstants;
    }

    public Enum(String name, String packagePath, ArrayList<EnumConstant> enumConstants) {
        super(name, packagePath);
        this.enumConstants = enumConstants;
    }

    public Enum(ArrayList<EnumConstant> enumConstants) {
        this.enumConstants = enumConstants;
    }

    public Enum(String name, String packagePath, HashSet<Keyword> keywords) {
        super(name, packagePath, keywords);
    }

    public Enum(String name, String packagePath) {
        super(name, packagePath);
    }

    public Enum() {
    }

    public ArrayList<EnumConstant> getEnumConstants() {
        return enumConstants;
    }

    public void setEnumConstants(ArrayList<EnumConstant> enumConstants) {
        this.enumConstants = enumConstants;
    }

    public void addEnumField(EnumConstant enumConstant) {
        enumConstants.add(enumConstant);
    }


}

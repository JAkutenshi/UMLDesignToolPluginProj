package com.jakutenshi.projects.umlplugin.container.entities;



import com.jakutenshi.projects.umlplugin.container.entities.attributes.Field;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Class extends UMLEntity{
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Method> methods = new ArrayList<>();
    private boolean isException = false;

    //TODO GENERIC CLASS

    @Override
    public String titleToUML() {
        StringBuilder builder = new StringBuilder();
        if (isException)
            builder.append("<<exception>> ");
        builder.append(getName());
        return builder.toString();
    }

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(titleToUML())
                .append("\n---------------------\n");
        for (Field field : fields) {
            builder.append(field.toUML())
                    .append("\n");
        }
        builder.append("---------------------\n");
        for (Method method : methods) {
            builder.append(method.toUML())
                    .append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toCode() {
        //TODO
        return null;
    }

    public Class(String name, String packagePath, HashSet<Keyword> keywords, ArrayList<Field> fields, ArrayList<Method> methods, boolean isException) {
        super(name, packagePath, keywords);
        this.fields = fields;
        this.methods = methods;
        this.isException = isException;
    }

    public Class(String name, String packagePath, ArrayList<Field> fields, ArrayList<Method> methods, boolean isException) {
        super(name, packagePath);
        this.fields = fields;
        this.methods = methods;
        this.isException = isException;
    }

    public Class(ArrayList<Field> fields, ArrayList<Method> methods, boolean isException) {
        this.fields = fields;
        this.methods = methods;
        this.isException = isException;
    }

    public Class(String name, String packagePath, HashSet<Keyword> keywords) {
        super(name, packagePath, keywords);
    }

    public Class(String name, String packagePath) {
        super(name, packagePath);
    }

    public Class() {
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }
}

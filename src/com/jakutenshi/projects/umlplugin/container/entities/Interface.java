package com.jakutenshi.projects.umlplugin.container.entities;

import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Method;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.TypeParameter;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Interface extends UMLEntity {
//атрибуты
    private ArrayList<TypeParameter> typeParameters = new ArrayList<>();
    private ArrayList<Method> signatures = new ArrayList<>();
//расширение другого интерфейса
    private String extendedInterface = null;


    @Override
    public String titleToUML() {
        return "<<interface>> " + getName();
    }

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(titleToUML())
                .append("\n_____________________\n");
        for (Method signature : signatures) {
            builder.append(signature.toUML())
                    .append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toCode() {
        //TODO
        return null;
    }

    public Interface(String name, String packagePath, HashSet<Keyword> keywords, ArrayList<Method> signatures) {
        super(name, packagePath, keywords);
        this.signatures = signatures;
    }

    public Interface(String name, String packagePath, ArrayList<Method> signatures) {
        super(name, packagePath);
        this.signatures = signatures;
    }

    public Interface(ArrayList<Method> signatures) {
        this.signatures = signatures;
    }

    public Interface(String name, String packagePath, HashSet<Keyword> keywords) {
        super(name, packagePath, keywords);
    }

    public Interface(String name, String packagePath) {
        super(name, packagePath);
    }

    public Interface() {
    }

    public ArrayList<Method> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<Method> signatures) {
        this.signatures = signatures;
    }

    public void addSignature(Method signature) {
        signatures.add(signature);
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

    public String getExtendedInterface() {
        return extendedInterface;
    }

    public void setExtendedInterface(String extendedInterface) {
        this.extendedInterface = extendedInterface;
    }
}

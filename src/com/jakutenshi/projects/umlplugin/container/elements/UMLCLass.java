package com.jakutenshi.projects.umlplugin.container.elements;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public class UMLClass extends UMLElement {
    private String title;
    private ArrayList<Field> fields;
    private ArrayList<Method> methods;
    private ArrayList<Parameter> template;

    public UMLClass(Stereotype aType, String aPath, String aName) {
        super(aType, aPath, aName);
        title = aType.toString() + aName;
        if (super.getType() != Stereotype.INTERFACE) {
            fields = new ArrayList<>();
        }
        methods = new ArrayList<>();
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

}

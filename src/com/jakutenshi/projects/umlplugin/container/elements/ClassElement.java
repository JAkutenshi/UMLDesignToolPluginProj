package com.jakutenshi.projects.umlplugin.container.elements;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class ClassElement extends UMLElement {
    private ArrayList<FieldDefinition> Fields;

    public ClassElement(String path, String name) {
        super(path, name);
    }
}

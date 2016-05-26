package com.jakutenshi.projects.umlplugin.container;

import com.jakutenshi.projects.umlplugin.container.elements.UMLElement;

import java.util.HashMap;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public class DiagramContainer {
    private HashMap<String, UMLElement> elementsContainer;

    public DiagramContainer() {
        elementsContainer = new HashMap<String, UMLElement>();
    }

    public void addUMLElement(UMLElement element) {
        elementsContainer.put(element.getPath(), element);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : elementsContainer.keySet()) {
            stringBuilder.append(key + " : " + elementsContainer.get(key) + '\n');
        }
        return stringBuilder.toString();
    }
}

package com.jakutenshi.projects.umlplugin.container;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.util.HashMap;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class DiagramContainer {
    HashMap<String, UMLEntity> umlEntities;
    private static final DiagramContainer instance = new DiagramContainer();

    private DiagramContainer() {
        umlEntities = new HashMap<>();
    }

    public static DiagramContainer getInstance() {
        return instance;
    }

    public void addUMLEntity(UMLEntity entity) {
        umlEntities.put(entity.getPackagePath(), entity);
    }

    public HashMap<String, UMLEntity> getUmlEntities() {
        return umlEntities;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String key : umlEntities.keySet()) {
            builder.append(umlEntities.get(key).getPackagePath())
                    .append('\n');

        }
        for (String key : umlEntities.keySet()) {
            builder.append("======================\n")
                    .append(umlEntities.get(key).toUML())
                    .append("\n======================\n");

        }
        return builder.toString();
    }
}

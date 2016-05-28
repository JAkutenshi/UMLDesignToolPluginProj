package com.jakutenshi.projects.umlplugin.container;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.util.HashMap;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class DiagramContainer {
    HashMap<String, UMLEntity> umlEntities = new HashMap<>();

    public void addUMLEntity(UMLEntity entity) {
        umlEntities.put(entity.getPackagePath(), entity);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String key : umlEntities.keySet()) {
            builder.append("====================\n")
                    .append(umlEntities.get(key).toUML())
                    .append("\n====================\n");

        }
        return builder.toString();
    }
}

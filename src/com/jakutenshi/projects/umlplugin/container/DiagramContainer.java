package com.jakutenshi.projects.umlplugin.container;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;
import com.jakutenshi.projects.umlplugin.drawers.UMLDrawer;
import com.jakutenshi.projects.umlplugin.util.Observable;
import com.jakutenshi.projects.umlplugin.util.UMLDiagramContainerObserver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class DiagramContainer implements Observable <UMLDiagramContainerObserver> {
    private HashMap<String, UMLEntity> umlEntities;
    private static final DiagramContainer instance = new DiagramContainer();
    private DiagramContainer() {
        umlEntities = new HashMap<>();
    }
    private ArrayList<UMLDiagramContainerObserver> observers = new ArrayList<>();


    public static DiagramContainer getInstance() {
        return instance;
    }

    public void addUMLEntity(UMLEntity entity) {
        umlEntities.put(entity.getPackagePath(), entity);
    }

    public void removeUMLEntity(UMLEntity entity) {
        umlEntities.remove(entity.getPackagePath());
    }

    public UMLEntity getUMLEntity(UMLEntity entity) {
        return umlEntities.get(entity.getPackagePath());
    }

    public void clearContainer() {
        umlEntities = new HashMap<>();
        System.gc();
    }

    public HashMap<String, UMLEntity> getUmlEntities() {
        return umlEntities;
    }

    public boolean containsKey(String key) {
        return  umlEntities.containsKey(key);
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

    @Override
    public void notifyObservers() {
        for (UMLDiagramContainerObserver observer : observers) {
            observer.onChange(umlEntities);
        }
    }

    @Override
    public void addObserver(UMLDiagramContainerObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(UMLDiagramContainerObserver observer) {
        observers.remove(observer);
    }

    public void drawLine(UMLDrawer drawer1, UMLDrawer drawer2) {

    }
}

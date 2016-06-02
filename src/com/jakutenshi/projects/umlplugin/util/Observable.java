package com.jakutenshi.projects.umlplugin.util;

/**
 * Created by JAkutenshi on 30.05.2016.
 */
public interface Observable <T> {

    void notifyObservers();

    void addObserver(T observer);

    void removeObserver(T  observer);

}

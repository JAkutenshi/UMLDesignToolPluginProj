package com.jakutenshi.projects.umlplugin.util;

/**
 * Created by JAkutenshi on 30.05.2016.
 */
public interface Observer<T> {

    void onChange(final T object);

}

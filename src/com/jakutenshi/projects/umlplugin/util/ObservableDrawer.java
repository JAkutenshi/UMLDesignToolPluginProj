package com.jakutenshi.projects.umlplugin.util;

import com.jakutenshi.projects.umlplugin.container.entities.UMLEntity;

import java.util.HashMap;

/**
 * Created by JAkutenshi on 02.06.2016.
 */
public interface ObservableDrawer {
    void onChange(String key, int x, int y);
}

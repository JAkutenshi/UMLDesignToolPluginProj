package com.jakutenshi.projects.umlplugin.container.elements;

import com.jakutenshi.projects.umlplugin.container.elements.MetodsSugnature;

import java.util.ArrayList;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public abstract class UMLElement {
    private ElementType Type;
    private String Path;
    private String Name;
    private ArrayList<MetodsSugnature> Methods;

    public UMLElement(String path, String name) {
        Path = path;
        Name = name;
    }

    public ElementType getType() {
        return Type;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "UMLElement{" +
                "Path='" + Path + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}

package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 25.05.2016.
 */
public abstract class UMLElement {
    private final Stereotype type;
    private String packagePath;
    private String name;
    private String key;

    public UMLElement(Stereotype aType, String aPackage, String aName) {
        type = aType;
        packagePath = aPackage;
        name = aName;
        computeKey();
    }

    public Stereotype getType() {
        return type;
    }

    public String getPackage() {
        return packagePath;
    }

    public void setPackage(String aPackage) {
        packagePath = aPackage;
        computeKey();
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
        computeKey();
    }

    public String getKey() {
        return key;
    }

    private void computeKey() {
        key = packagePath + '.' + name;
    }
}

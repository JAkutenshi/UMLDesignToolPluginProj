package com.jakutenshi.projects.umlplugin.container.entities;


import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Scope;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public abstract class UMLEntity extends UMLElement implements Generatable {
    private String name = "noName";
    private String packagePath = "noPackage";
    private Scope scope = Scope.PACKAGE;
    private String key = "noKey";
    private String extend; //ToDO CodeGeneration

    public UMLEntity(String name, String packagePath, Scope scope) {
        this.name = name;
        this.packagePath = packagePath;
        this.scope = scope;
        constructKey();
    }

    public UMLEntity(String name, String packagePath) {
        this.name = name;
        this.packagePath = packagePath;
        constructKey();
    }

    public UMLEntity() {
    }

    public String getKey() {
        return key;
    }

    private void constructKey() {
        key = packagePath + '.' + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public abstract String titleToUML();

}

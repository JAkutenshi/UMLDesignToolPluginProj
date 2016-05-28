package com.jakutenshi.projects.umlplugin.container.entities;


import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Keyword;
import com.jakutenshi.projects.umlplugin.container.entities.attributes.Scope;

import java.util.HashSet;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public abstract class UMLEntity extends UMLElement implements Generatable {
    private String name = "noName";
    private String packagePath = "noPackage";
    private Scope scope = Scope.PACKAGE;
    private HashSet<Keyword> keywords = new HashSet<>();
    private String extend; //ToDO CodeGeneration

    public UMLEntity(String name, String packagePath, HashSet<Keyword> keywords) {
        this.name = name;
        this.packagePath = packagePath;
        this.keywords = keywords;
    }

    public UMLEntity(String name, String packagePath) {
        this.name = name;
        this.packagePath = packagePath;
    }

    public UMLEntity() {
    }

    public HashSet<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(HashSet<Keyword> keywords) {
        this.keywords = keywords;
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

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public abstract String titleToUML();

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}

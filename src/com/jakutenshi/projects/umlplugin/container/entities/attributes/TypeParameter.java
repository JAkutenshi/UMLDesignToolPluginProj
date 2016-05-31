package com.jakutenshi.projects.umlplugin.container.entities.attributes;

/**
 * Created by JAkutenshi on 30.05.2016.
 */
public class TypeParameter extends EnumConstant {
    private String extendsLimitation = null;

    @Override
    public String toUML() {
        return super.toUML() + " : Class";
    }

    @Override
    public String toCode() {
        return super.toCode();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public TypeParameter(String name) {
        super(name);
    }

    public TypeParameter() {
        super();
    }

    public String getExtendsLimitation() {
        return extendsLimitation;
    }

    public void setExtendsLimitation(String extendsLimitation) {
        this.extendsLimitation = extendsLimitation;
    }
}

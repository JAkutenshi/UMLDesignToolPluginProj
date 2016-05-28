package com.jakutenshi.projects.umlplugin.container.entities.attributes;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class Method extends Signature {
    private Scope scope;

    @Override
    public String toUML() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toUML())
                .append(" ")
                .append(super.toUML());

        return builder.toString();
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(scope.toCode())
                .append(" ")
                .append(super.toCode());
        int semicolon = builder.length() - 1;
        builder.replace(semicolon, semicolon, " {\n\n}");
        return builder.toString();
    }
}

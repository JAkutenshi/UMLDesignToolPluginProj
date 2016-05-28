package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public enum Property {
    READ_ONLY {
        @Override
        public String toString() {
            return "{readOnly}";
        }
    },
    NONE {
        @Override
        public String toString() {
            return "";
        }
    };

    public abstract String toString();
}

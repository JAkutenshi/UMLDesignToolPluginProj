package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public enum Modifier {
    PUBLIC {
        @Override
        public String toString() {
            return "+";
        }
    },
    PRIVATE {
        @Override
        public String toString() {
            return "-";
        }
    },
    PACKAGE {
        @Override
        public String toString() {
            return "~";
        }
    },
    PROTECTED {
        @Override
        public String toString() {
            return "#";
        }
    };

    public abstract String toString();
}

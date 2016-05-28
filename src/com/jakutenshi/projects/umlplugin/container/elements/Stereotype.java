package com.jakutenshi.projects.umlplugin.container.elements;

/**
 * Created by JAkutenshi on 27.05.2016.
 */
public enum Stereotype {
    CLASS {
        @Override
        public String toString() {
            return "";
        }
    },
    INTERFACE {
        @Override
        public String toString() {
            return "<<interface>> ";
        }
    },
    EXCEPTION {
        @Override
        public String toString() {
            return "<<exception>> ";
        }
    },
    ENUMERATION {
        @Override
        public String toString() {
            return "<<enumeration>> ";
        }
    },
    OBJECT {
        @Override
        public String toString() {
            return "Object";
        }
    };

    @Override
    public abstract String toString();
}

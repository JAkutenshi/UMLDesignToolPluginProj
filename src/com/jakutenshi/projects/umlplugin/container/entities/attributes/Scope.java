package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Generatable;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public enum Scope implements Generatable {
    PRIVATE {
        @Override
        public String toUML() {
            return "-";
        }

        @Override
        public String toCode() {
            return "private";
        }
    },
    PROTECTED {
        @Override
        public String toUML() {
            return "#";
        }

        @Override
        public String toCode() {
            return "protected";
        }
    },
    PACKAGE {
        @Override
        public String toUML() {
            return "~";

        }

        @Override
        public String toCode() {
            return "package";
        }
    },
    PUBLIC {
        @Override
        public String toUML() {
            return "+";
        }

        @Override
        public String toCode() {
            return "public";
        }
    };
}

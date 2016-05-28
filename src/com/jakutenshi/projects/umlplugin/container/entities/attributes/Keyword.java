package com.jakutenshi.projects.umlplugin.container.entities.attributes;

import com.jakutenshi.projects.umlplugin.container.UMLElement;
import com.jakutenshi.projects.umlplugin.container.entities.Generatable;

/**
 * Created by JAkutenshi on 28.05.2016.
 */
public enum Keyword implements Generatable {
    FINAL {
        @Override
        public String toUML() {
            return " {readOnly}";
        }

        @Override
        public String toCode() {
            return "final";
        }
    },
    ABSTRACT {
        @Override
        public String toUML() {
            return "";
        }

        @Override
        public String toCode() {
            return "abstract";
        }
    },
    STATIC {
        @Override
        public String toUML() {
            return "";
        }

        @Override
        public String toCode() {
            return "static";
        }
    }
}

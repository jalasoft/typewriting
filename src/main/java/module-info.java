module psani.na.stroji {
    requires spring.boot.starter;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.sql;
    requires spring.web;
    requires reactor.core;

    opens cz.jalasoft.psaninastroji to spring.core;

    exports cz.jalasoft.psaninastroji to spring.beans, spring.context;
    exports cz.jalasoft.psaninastroji.controller to spring.beans, spring.web;
}
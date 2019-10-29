module typewriting.classic {

    requires typewriting.model;

	requires spring.boot.starter;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires spring.boot.starter.web;


    requires java.xml;
    requires java.sql;

    opens cz.jalasoft.typewriting to spring.core;

    exports cz.jalasoft.typewriting;
    exports cz.jalasoft.typewriting.web;

}
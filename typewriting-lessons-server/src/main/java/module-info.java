module typewriting.lessons.server {

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

    requires java.annotation;
    requires java.xml;
    requires java.sql;

    requires com.fasterxml.jackson.databind;

    opens cz.jalasoft.typewriting to spring.core;
	opens cz.jalasoft.typewriting.config to spring.core;
    opens cz.jalasoft.typewriting.web to com.fasterxml.jackson.databind;

    exports cz.jalasoft.typewriting;
    exports cz.jalasoft.typewriting.config;
    exports cz.jalasoft.typewriting.web;

}
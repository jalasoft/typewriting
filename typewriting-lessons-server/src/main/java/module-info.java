module typewriting.lessons.server {

    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.beans;

    requires javax.servlet.api;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;

    requires java.xml;
    requires typewriting.model;
    requires com.fasterxml.jackson.databind;

    opens cz.jalasoft.typewriting to spring.core;
	opens cz.jalasoft.typewriting.config to spring.core;
    opens cz.jalasoft.typewriting.web to com.fasterxml.jackson.databind;

    exports cz.jalasoft.typewriting;
    exports cz.jalasoft.typewriting.config;
    exports cz.jalasoft.typewriting.web;

}
module com.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires transitive javafx.graphics;

    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires org.slf4j;
    requires ch.qos.logback.classic;

    requires java.sql;
    requires java.net.http;

    opens com.studentmanagementsystem to javafx.fxml, spring.core, spring.context, spring.beans;
    opens com.studentmanagementsystem.controller to javafx.fxml, spring.core, spring.context, spring.beans;
    opens com.studentmanagementsystem.model to com.fasterxml.jackson.databind, spring.core, spring.context, spring.beans, javafx.base;
    opens com.studentmanagementsystem.service to spring.core, spring.context, spring.beans;

    exports com.studentmanagementsystem;
    exports com.studentmanagementsystem.model;
    exports com.studentmanagementsystem.service;
    exports com.studentmanagementsystem.controller;
}

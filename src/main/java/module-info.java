module com.studentmanagementsystem {
    // JavaFX dependencies
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires transitive javafx.graphics;
    
    // Spring Framework dependencies
    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    
    // Jackson dependencies
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;  // ADD THIS LINE
    
    // Additional required modules
    requires java.sql;
    requires java.net.http;
    requires org.slf4j;
    
    // Open packages for reflection
    opens com.studentmanagementsystem to javafx.fxml, spring.core;
    opens com.studentmanagementsystem.controller to javafx.fxml, spring.core;
    opens com.studentmanagementsystem.model to com.fasterxml.jackson.databind, spring.core;
    opens com.studentmanagementsystem.service to spring.core;
    
    // Export packages
    exports com.studentmanagementsystem;
    exports com.studentmanagementsystem.model;
    exports com.studentmanagementsystem.service;
    exports com.studentmanagementsystem.controller;
}
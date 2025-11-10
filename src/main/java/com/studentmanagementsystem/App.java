package com.studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/studentmanagementsystem/FXMLDocument.fxml"));
        
         Scene scene = new Scene(root);
        
        primaryStage.setTitle("Inventory");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1350);  // Same width
        primaryStage.setHeight(720);  // Reduced by 20% (from 900 to 720)
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(600); // Reduced min height
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
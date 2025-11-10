// package com.studentmanagementsystem.controller;

// import java.io.IOException;
// import javafx.fxml.FXML;
// import javafx.scene.control.Alert;
// import com.studentmanagementsystem.App;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// public class SecondaryController {
//     private static final Logger logger = LoggerFactory.getLogger(SecondaryController.class);

//     @FXML
//     private void switchToPrimary() {
//         try {
//             App.setRoot("primary");
//         } catch (IOException e) {
//             logger.error("Failed to switch to primary view", e);
//             showError("Navigation Error", "Unable to load primary view");
//         }
//     }

//     private void showError(String title, String content) {
//         Alert alert = new Alert(Alert.AlertType.ERROR);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(content);
//         alert.showAndWait();
//     }
// }
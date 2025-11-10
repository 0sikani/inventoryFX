// package com.studentmanagementsystem.controller;

// import java.io.IOException;
// import javafx.fxml.FXML;
// import javafx.scene.control.Alert;
// import com.studentmanagementsystem.App;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// public class PrimaryController {
//     private static final Logger logger = LoggerFactory.getLogger(PrimaryController.class);

//     @FXML
//     private void switchToSecondary() {
//         try {
//             App.setRoot("secondary");
//         } catch (IOException e) {
//             logger.error("Failed to switch to secondary view", e);
//             showError("Navigation Error", "Unable to load secondary view");
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

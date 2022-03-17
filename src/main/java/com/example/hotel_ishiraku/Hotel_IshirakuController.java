package com.example.hotel_ishiraku;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Hotel_IshirakuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
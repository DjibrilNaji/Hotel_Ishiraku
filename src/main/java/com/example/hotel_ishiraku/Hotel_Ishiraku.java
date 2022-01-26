package com.example.hotel_ishiraku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Hotel_Ishiraku extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Disponibilites.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ConsulterLavageLaveur.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("ConsulterLavageReceptionniste.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


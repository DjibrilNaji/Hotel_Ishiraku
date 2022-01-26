package com.example.hotel_ishiraku.sommaire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SommaireLaveurController {

    @FXML
    private Button btn_consulter_lavage;

    @FXML
    private Button btn_deconnexion;

    public void lavage(ActionEvent actionEvent) throws IOException {
        btn_consulter_lavage.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/ConsulterLavageLaveur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void deconnexion(ActionEvent actionEvent) throws IOException {
        btn_deconnexion.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Login.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}

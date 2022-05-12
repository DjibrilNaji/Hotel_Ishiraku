package com.example.hotel_ishiraku.sommaire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SommaireReceptionnisteController {

    @FXML
    private Button btn_consulter_client;

    @FXML
    private Button btn_consulter_employes;

    @FXML
    private Button btn_consulter_lavage;

    @FXML
    private Button btn_consulter_place;

    @FXML
    private Button btn_consulter_reservation;

    @FXML
    private Button btn_deconnexion;


    @FXML
    private Button btn_consulter_choix;


    public void consulter_client(ActionEvent actionEvent) throws IOException {
        btn_consulter_client.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Client.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void consulter_employes(ActionEvent actionEvent) throws IOException {
        btn_consulter_employes.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Employes.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void consulter_reservation(ActionEvent actionEvent) throws IOException {
        btn_consulter_reservation.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Reservation.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void consulter_place(ActionEvent actionEvent) throws IOException {
        btn_consulter_place.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Disponibilites.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void consulter_lavage(ActionEvent actionEvent) throws IOException {
        btn_consulter_lavage.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/ConsulterLavageReceptionniste.fxml"));
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



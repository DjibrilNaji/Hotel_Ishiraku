package com.example.hotel_ishiraku.lavage;

import com.example.hotel_ishiraku.Mysqlconnect;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LavageLaveurController implements Initializable {

    @FXML
    private TableView<Lavage> table_lavage;

    @FXML
    private TableColumn<Lavage, Integer> col_id;

    @FXML
    private TableColumn<Lavage, String> col_laveur;

    @FXML
    private TableColumn<Lavage, String> col_date;

    @FXML
    private TableColumn<Lavage, String> col_heure;

    @FXML
    private TableColumn<Lavage, String> col_voiture;

    @FXML
    private TableColumn<Lavage, String> col_commentaire;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;

    ObservableList<Lavage> listM;
    ObservableList<Lavage> dataList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_user();
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<Lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<Lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<Lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<Lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<Lavage, String>("commentaire"));

        listM = new Mysqlconnect().getDataLavageLaveur();
        table_lavage.setItems(listM);
    }

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<Lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<Lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<Lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<Lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<Lavage, String>("commentaire"));

        dataList = new Mysqlconnect().getDataLavageLaveur();
        table_lavage.setItems(dataList);

        FilteredList<Lavage> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (person.getHeure().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else
                    return false; // Does not match.
            });
        });
        SortedList<Lavage> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_lavage.comparatorProperty());
        table_lavage.setItems(sortedData);
    }

    public void sommaire(ActionEvent actionEvent) throws IOException {
        btn_accueil.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireLaveur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}

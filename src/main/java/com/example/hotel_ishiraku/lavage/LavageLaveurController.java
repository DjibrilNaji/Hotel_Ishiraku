package com.example.hotel_ishiraku.lavage;

import com.example.hotel_ishiraku.Mysqlconnect;
import javafx.collections.ObservableList;
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
    Button btn_accueil;

    ObservableList<Lavage> listM;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<Lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<Lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<Lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<Lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<Lavage, String>("commentaire"));

        listM = new Mysqlconnect().getDataLavage();
        table_lavage.setItems(listM);
    }

//    @FXML
//    void search_user() {
//        col_id.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("id"));
//        col_laveur.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("laveur"));
//        col_date.setCellValueFactory(new PropertyValueFactory<lavage, String>("date"));
//        col_heure.setCellValueFactory(new PropertyValueFactory<lavage, String>("heure"));
//        col_voiture.setCellValueFactory(new PropertyValueFactory<lavage, String>("voiture"));
//        col_commentaire.setCellValueFactory(new PropertyValueFactory<lavage, String>("commentaire"));
//
//        dataList = mysqlconnect.getDataLavage();
//        table_lavage.setItems(dataList);
//        FilteredList<lavage> filteredData = new FilteredList<>(dataList, b -> true);
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
////            filteredData.setPredicate(person -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (person.getHeure().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//                    return true; // Filter matches username
//                } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches password
//                }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches password
//                }
//                else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
//                    return true;// Filter matches email
//
//                else
//                    return false; // Does not match.
//            });
//        });
//        SortedList<lavage> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(table_lavage.comparatorProperty());
//        table_lavage.setItems(sortedData);
//    }

    public void sommaire(ActionEvent actionEvent) throws IOException {
        btn_accueil.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireLaveur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}

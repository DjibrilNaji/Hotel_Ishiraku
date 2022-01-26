package com.example.hotel_ishiraku.lavage;

import com.example.hotel_ishiraku.mysqlconnect;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LavageLaveurController implements Initializable {

    @FXML
    private TableView<lavage> table_lavage;

    @FXML
    private TableColumn<lavage, Integer> col_id;

    @FXML
    private TableColumn<lavage, Integer> col_laveur;

    @FXML
    private TableColumn<lavage, String> col_date;

    @FXML
    private TableColumn<lavage, String> col_heure;

    @FXML
    private TableColumn<lavage, String> col_voiture;

    @FXML
    private TableColumn<lavage, String> col_commentaire;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_laveur;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_heure;

    @FXML
    private TextField txt_voiture;

    @FXML
    private TextField txt_commentaire;

    @FXML
    private DatePicker filterField;

    @FXML
    Button btn_accueil;

    ObservableList<lavage> listM;
    ObservableList<lavage> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("laveur"));
        col_date.setCellValueFactory(new PropertyValueFactory<lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<lavage, String>("commentaire"));

        listM = mysqlconnect.getDataLavage();
        table_lavage.setItems(listM);
    }

    //
//
//
//
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
//    search_user();
        // Code Source in description
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

package com.example.hotel_ishiraku.lavage;

import com.example.hotel_ishiraku.DAO.LavageDAO;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


public class LavageReceptionnisteController implements Initializable {

    @FXML
    private TableView<Lavage> table_lavage;

    @FXML
    private TableColumn<Lavage, Integer> col_id;

    @FXML
    private TableColumn<Lavage, Integer> col_idLaveur;

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
    private TextField txt_id;

    @FXML
    private TextField txt_idLaveur;

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
    private TextField filterField;

    @FXML
    private Button btn_accueil;


    ObservableList<Lavage> listM;
    ObservableList<Lavage> dataList;

    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_user();
    }

    @FXML
    void clear() {
        txt_id.setText(null);
        txt_idLaveur.setText(null);
        txt_laveur.setText(null);
        txt_date.setText(null);
        txt_heure.setText(null);
        txt_voiture.setText(null);
        txt_commentaire.setText(null);
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_lavage.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_idLaveur.setText(col_idLaveur.getCellData(index).toString());
        txt_laveur.setText(col_laveur.getCellData(index).toString());
        txt_date.setText(col_date.getCellData(index).toString());
        txt_heure.setText(col_heure.getCellData(index).toString());
        txt_voiture.setText(col_voiture.getCellData(index).toString());
        txt_commentaire.setText(col_commentaire.getCellData(index).toString());
    }

    public void addLavage() {
        Lavage lavage = new Lavage(Integer.parseInt(txt_idLaveur.getText()), txt_date.getText(), txt_heure.getText(), txt_voiture.getText(), txt_commentaire.getText());
        new LavageDAO().addLavage(lavage);
        updateTable();
    }

    public void editLavage() {
        Lavage lavage = new Lavage(Integer.parseInt(txt_id.getText()), Integer.parseInt(txt_idLaveur.getText()), txt_date.getText(), txt_heure.getText(), txt_voiture.getText(), txt_commentaire.getText());
        new LavageDAO().editLavage(lavage);
        updateTable();
    }

    public void deleteLavage() {
        Lavage lavage = new Lavage(Integer.parseInt(txt_id.getText()));
        new LavageDAO().delete(lavage);
        updateTable();
    }

    @FXML
    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Lavage, Integer>("id"));
        col_idLaveur.setCellValueFactory(new PropertyValueFactory<Lavage, Integer>("laveur"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<Lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<Lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<Lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<Lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<Lavage, String>("commentaire"));

        listM = new Mysqlconnect().getDataLavage();
        table_lavage.setItems(listM);
    }

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<Lavage, Integer>("id"));
        col_idLaveur.setCellValueFactory(new PropertyValueFactory<Lavage, Integer>("laveur"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<Lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<Lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<Lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<Lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<Lavage, String>("commentaire"));

        dataList = new Mysqlconnect().getDataLavage();
        table_lavage.setItems(dataList);
        FilteredList<Lavage> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getVoiture().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (person.getHeure().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
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
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireReceptionniste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}

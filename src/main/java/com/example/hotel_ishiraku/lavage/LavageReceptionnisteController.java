package com.example.hotel_ishiraku.lavage;

import com.example.hotel_ishiraku.mysqlconnect;
import javafx.collections.FXCollections;
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
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LavageReceptionnisteController implements Initializable {

    @FXML
    private TableView<lavage> table_lavage;

    @FXML
    private TableColumn<lavage, Integer> col_id;

    @FXML
    private TableColumn<lavage, String> col_laveur;

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
    private TextField filterField;

    @FXML
    private Button btn_accueil;

    ObservableList<lavage> listM;
    ObservableList<lavage> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_lavage() {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into ishiraku_lavage(laveur,date, heure,voiture,commentaire) values (?,?,?,?,?)";

        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_laveur.getText());
            pst.setString(2, txt_date.getText());
            pst.setString(3, txt_heure.getText());
            pst.setString(4, txt_voiture.getText());
            pst.setString(5, txt_commentaire.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Lavage ajouter avec succès");
            UpdateTable();
//            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    @FXML
    void clearEvent(ActionEvent event) {
        clear();
    }

    //////// methode select lavage ///////
    @FXML
    void getSelected(MouseEvent event) {
        index = table_lavage.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_laveur.setText(col_laveur.getCellData(index).toString());
        txt_date.setText(col_date.getCellData(index).toString());
        txt_heure.setText(col_heure.getCellData(index).toString());
        txt_voiture.setText(col_voiture.getCellData(index).toString());
        txt_commentaire.setText(col_commentaire.getCellData(index).toString());

    }

    public void Edit() {
        conn = mysqlconnect.ConnectDb();
        String sql = "update ishiraku_lavage set id= ?, laveur= ?,date= ?,heure= ?,voiture= ?,commentaire= ? where id = id ";

        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_laveur.getText());
            pst.setString(3, txt_date.getText());
            pst.setString(4, txt_heure.getText());
            pst.setString(5, txt_voiture.getText());
            pst.setString(6, txt_commentaire.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from ishiraku_lavage where id= ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<lavage, String>("commentaire"));

        listM = mysqlconnect.getDataLavage();
        table_lavage.setItems(listM);
    }

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<lavage, Integer>("id"));
        col_laveur.setCellValueFactory(new PropertyValueFactory<lavage, String>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<lavage, String>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<lavage, String>("heure"));
        col_voiture.setCellValueFactory(new PropertyValueFactory<lavage, String>("voiture"));
        col_commentaire.setCellValueFactory(new PropertyValueFactory<lavage, String>("commentaire"));

        dataList = mysqlconnect.getDataLavage();
        table_lavage.setItems(dataList);
        FilteredList<lavage> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(person -> {
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
        });
        SortedList<lavage> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_lavage.comparatorProperty());
        table_lavage.setItems(sortedData);
    }

    void clear() {
        txt_id.setText(null);
        txt_laveur.setText(null);
        txt_date.setText(null);
        txt_heure.setText(null);
        txt_voiture.setText(null);
        txt_commentaire.setText(null);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
//    search_user();
        // Code Source in description
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

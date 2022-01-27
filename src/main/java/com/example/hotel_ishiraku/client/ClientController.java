package com.example.hotel_ishiraku.client;

import com.example.hotel_ishiraku.mysqlconnect;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class ClientController implements Initializable {

    @FXML
    private TableView<client> table_client;

    @FXML
    private TableColumn<client, Integer> col_id;

    @FXML
    private TableColumn<client, String> col_nom;

    @FXML
    private TableColumn<client, String> col_prenom;

    @FXML
    private TableColumn<client, String> col_numero;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_numero;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;

    @FXML
    private Button btn_reserver;

    ObservableList<client> listM;
    ObservableList<client> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_client() {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into ishiraku_client(nom,prenom, numero_telephone) values (?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_nom.getText());
            pst.setString(2, txt_numero.getText());
            pst.setString(3, txt_numero.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Client ajouter avec succès");
            UpdateTable();
//            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //////// methode select lavage ///////
    @FXML
    void getSelected(MouseEvent event) {
        index = table_client.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_nom.setText(col_nom.getCellData(index).toString());
        txt_prenom.setText(col_prenom.getCellData(index).toString());
        txt_numero.setText(col_numero.getCellData(index).toString());
    }

    public void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_nom.getText();
            String value3 = txt_prenom.getText();
            String value4 = txt_numero.getText();

            String sql = "update ishiraku_client set nom= '" + value2 + "',prenom= '" + value3 + "',numero_telephone= '" +
                    value4 + "' where id= '" + value1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Client modifié");
            UpdateTable();
//            search_client();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from ishiraku_client where id= ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Client supprimé avec succès");
            UpdateTable();
//            search_client();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<client, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<client, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<client, String>("prenom"));
        col_numero.setCellValueFactory(new PropertyValueFactory<client, String>("numero_telephone"));

        listM = mysqlconnect.getDataClient();
        table_client.setItems(listM);
    }

//    @FXML
//    void search_client() {
//        col_id.setCellValueFactory(new PropertyValueFactory<client, Integer>("id"));
//        col_nom.setCellValueFactory(new PropertyValueFactory<client, String>("nom"));
//        col_prenom.setCellValueFactory(new PropertyValueFactory<client, String>("prenom"));
//        col_numero.setCellValueFactory(new PropertyValueFactory<client, String>("numero_telephone"));
//
//        dataList = com.example.lavage_laveur.mysqlconnect.getDataClient();
//        table_client.setItems(dataList);
//        FilteredList<client> filteredData = new FilteredList<>(dataList, b -> true);
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
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
//        });
//        SortedList<client> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(table_client.comparatorProperty());
//        table_client.setItems(sortedData);
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
//    search_dispo();
    }

    public void sommaire(ActionEvent actionEvent) throws IOException {
        btn_accueil.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireReceptionniste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void reserver_place(ActionEvent actionEvent) throws IOException {
        btn_reserver.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/Disponibilites.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
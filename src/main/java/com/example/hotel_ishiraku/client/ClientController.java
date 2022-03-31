package com.example.hotel_ishiraku.client;

import com.example.hotel_ishiraku.DAO.ClientDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TableView<Client> table_client;

    @FXML
    private TableColumn<Client, Integer> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_prenom;

    @FXML
    private TableColumn<Client, String> col_numero;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_numero;

    @FXML
    private javafx.scene.control.Button btn_accueil;

    @FXML
    private javafx.scene.control.Button btn_reserver;


    @FXML
    private TextField filterField;

    ObservableList<Client> listM;
    ObservableList<Client> dataList;

    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_client();
    }

    public void Add() {
        new ClientDAO().Add_client(txt_nom.getText(), txt_prenom.getText(), txt_numero.getText());
        UpdateTable();
    }

    public void Edit() {
        new ClientDAO().Edit_client(txt_id.getText(), txt_nom.getText(), txt_prenom.getText(), txt_numero.getText());
        UpdateTable();
    }

    public void Delete() {
        new ClientDAO().Delete_client(txt_id.getText());
        UpdateTable();
    }

    @FXML
    void clear(ActionEvent event) {
        txt_id.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_numero.setText(null);
    }

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

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        col_numero.setCellValueFactory(new PropertyValueFactory<Client, String>("numero_telephone"));

        listM = new Mysqlconnect().getDataClient();
        table_client.setItems(listM);
    }

    public void search_client() {
        col_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        col_numero.setCellValueFactory(new PropertyValueFactory<Client, String>("numero_telephone"));

        dataList = new Mysqlconnect().getDataClient();

        table_client.setItems(dataList);

        FilteredList<Client> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else
                    return false; // Does not match.
            });
        });
        SortedList<Client> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_client.comparatorProperty());
        table_client.setItems(sortedData);
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
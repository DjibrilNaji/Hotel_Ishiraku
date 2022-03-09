package com.example.hotel_ishiraku.disponibilite;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


public class DisponibilteController implements Initializable {

    @FXML
    private TableView<disponibilite> table_disponibilite;

    @FXML
    private TableColumn<disponibilite, Integer> col_id;

    @FXML
    private TableColumn<disponibilite, Integer> col_etage;

    @FXML
    private TableColumn<disponibilite, Integer> col_numParking;

    @FXML
    private TableColumn<disponibilite, String> col_categorie;

    @FXML
    private TableColumn<disponibilite, String> col_typeVoiture;

    @FXML
    private TableColumn<disponibilite, Integer> col_idClient;

    @FXML
    private TableColumn<disponibilite, String> col_client;

    @FXML
    private TextField txt_id;

//    @FXML
//    private TextField txt_etage;
//
//    @FXML
//    private TextField txt_numParking;
//
//    @FXML
//    private TextField txt_typeVoiture;
//
//    @FXML
//    private TextField txt_categorie;

    @FXML
    private TextField txt_client;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;

    ObservableList<disponibilite> listM;
    ObservableList<disponibilite> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    //////// methode select lavage ///////
    @FXML
    void getSelected(MouseEvent event) {
        index = table_disponibilite.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_client.setText(col_idClient.getCellData(index).toString());

    }

    public void Edit() {
        try {
            conn = com.example.hotel_ishiraku.mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_client.getText();

            String sql = "update ishiraku_place set id_client= '" + value2 + "' where id= '" + value1 + "' ";

            pst = conn.prepareStatement(sql);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès");
            UpdateTable();
//            search_dispo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("numParking"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("categorie"));
        col_typeVoiture.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("typevoiture"));
        col_idClient.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("id_client"));
        col_client.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("nom"));

        listM = com.example.hotel_ishiraku.mysqlconnect.getDataPlace();

        table_disponibilite.setItems(listM);
    }

    @FXML
    void search_dispo() {
        col_id.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("numParking"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("categorie"));
        col_typeVoiture.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("typevoiture"));
        col_idClient.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("client"));
        col_client.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("nom"));

        dataList = com.example.hotel_ishiraku.mysqlconnect.getDataPlace();

        table_disponibilite.setItems(dataList);

        FilteredList<disponibilite> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(place -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(place.getEtage()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else if (place.getTypevoiture().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else if (place.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else
                    return false; // Does not match.
            });
        });

        SortedList<disponibilite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_disponibilite.comparatorProperty());
        table_disponibilite.setItems(sortedData);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
//        search_dispo();
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

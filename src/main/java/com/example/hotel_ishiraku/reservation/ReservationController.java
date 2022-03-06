package com.example.hotel_ishiraku.reservation;


import com.example.hotel_ishiraku.mysqlconnect;
import com.example.hotel_ishiraku.reservation.reservation;
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
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private TableView<reservation> table_reservation;

    @FXML
    private TableColumn<reservation, Integer> col_id;

    @FXML
    private TableColumn<reservation, String> col_date_arrivee;

    @FXML
    private TableColumn<reservation, String> col_date_sortie;

    @FXML
    private TableColumn<reservation, Integer> col_place;

    @FXML
    private Button btn_accueil;

    @FXML
    private Button btn_reservation;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField date_entree;

    @FXML
    private TextField date_sortie;

    @FXML
    private TextField txt_place;


    ObservableList<reservation> listM;
    ObservableList<reservation> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void getSelected(MouseEvent event) {
        index = table_reservation.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        date_entree.setText(col_date_arrivee.getCellData(index).toString());
        date_sortie.setText(col_date_sortie.getCellData(index).toString());
        txt_place.setText(col_place.getCellData(index).toString());

    }

    public void Add_reservation() {
        conn = mysqlconnect.ConnectDb();
        String sql = "INSERT INTO `ishiraku_reservation` (`client`, `dateEntree`, `dateSortie`, `place`) VALUES (?,?,?,?);";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, txt_id.getText());
            pst.setString(2, date_entree.getText());
            pst.setString(3, date_sortie.getText());
            pst.setString(4, txt_place.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Reservation ajouter avec succès");
            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = date_entree.getText();
            String value3 = date_sortie.getText();
            String value4 = txt_place.getText();

            String sql = "UPDATE `ishiraku_reservation` SET client='" + value1 + "', dateEntree= '" + value2 + "',dateSortie= '" + value3 + "',place= '" + value4 + "' " +
                    "where place= '" + value4 + "'";

            pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from ishiraku_reservation where place= ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_place.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void UpdateTable() {

        col_id.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("client"));
        col_date_arrivee.setCellValueFactory(new PropertyValueFactory<reservation, String>("dateEntree"));
        col_date_sortie.setCellValueFactory(new PropertyValueFactory<reservation, String>("dateSortie"));
        col_place.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("place"));

        listM = mysqlconnect.getDataReservation();
        table_reservation.setItems(listM);
    }

    public void sommaire(ActionEvent actionEvent) throws IOException {
        btn_accueil.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireReceptionniste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}

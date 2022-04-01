package com.example.hotel_ishiraku.reservation;


import com.example.hotel_ishiraku.DAO.ReservationDAO;
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
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private TableView<Reservation> table_reservation;

    @FXML
    private TableColumn<Reservation, Integer> col_id;

    @FXML
    private TableColumn<Reservation, String> col_date_arrivee;

    @FXML
    private TableColumn<Reservation, String> col_date_sortie;

    @FXML
    private TableColumn<Reservation, Integer> col_place;

    @FXML
    private Button btn_accueil;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField dateArrivee;

    @FXML
    private TextField dateSortie;

    @FXML
    private TextField txt_place;

    ObservableList<Reservation> listM;

    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }

    @FXML
    void clear() {
        txt_id.setText(null);
        dateArrivee.setText(null);
        dateSortie.setText(null);
        txt_place.setText(null);
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_reservation.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        dateArrivee.setText(col_date_arrivee.getCellData(index).toString());
        dateSortie.setText(col_date_sortie.getCellData(index).toString());
        txt_place.setText(col_place.getCellData(index).toString());
    }

    public void add() {
        Reservation reservation = new Reservation(Integer.parseInt(txt_id.getText()), dateArrivee.getText(), dateSortie.getText(), Integer.parseInt(txt_place.getText()));
        new ReservationDAO().addReservation(reservation);
        updateTable();
    }

    public void delete() {
        Reservation reservation = new Reservation(Integer.parseInt(txt_place.getText()));
        new ReservationDAO().deleteReservation(reservation);
        updateTable();
    }

    public void edit() {
        Reservation reservation = new Reservation(Integer.parseInt(txt_id.getText()), dateArrivee.getText(), dateSortie.getText(), Integer.parseInt(txt_place.getText()));
        new ReservationDAO().editReservation(reservation);
        updateTable();
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("client"));
        col_date_arrivee.setCellValueFactory(new PropertyValueFactory<Reservation, String>("dateEntree"));
        col_date_sortie.setCellValueFactory(new PropertyValueFactory<Reservation, String>("dateSortie"));
        col_place.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("place"));

        listM = new Mysqlconnect().getDataReservation();
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
}

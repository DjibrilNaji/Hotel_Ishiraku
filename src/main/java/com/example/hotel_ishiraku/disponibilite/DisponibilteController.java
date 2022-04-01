package com.example.hotel_ishiraku.disponibilite;

import com.example.hotel_ishiraku.DAO.DisponibiliteDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisponibilteController implements Initializable {

    @FXML
    private TableView<Disponibilite> table_disponibilite;

    @FXML
    private TableColumn<Disponibilite, Integer> col_id;

    @FXML
    private TableColumn<Disponibilite, Integer> col_etage;

    @FXML
    private TableColumn<Disponibilite, Integer> col_numParking;

    @FXML
    private TableColumn<Disponibilite, String> col_categorie;

    @FXML
    private TableColumn<Disponibilite, String> col_typeVoiture;

    @FXML
    private TextField txt_dateSortie;

    @FXML
    private TextField txt_dateArrivee;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;

    ObservableList<Disponibilite> listM;
    ObservableList<Disponibilite> dataList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_dispo();
    }

    public void dispo() {
        new DisponibiliteDAO().dispoPlace(txt_dateArrivee.getText(), txt_dateSortie.getText());
        updateTable();
    }

    @FXML
    void clear() {
        txt_dateArrivee.setText(null);
        txt_dateSortie.setText(null);
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("numParking"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<Disponibilite, String>("categorie"));
        col_typeVoiture.setCellValueFactory(new PropertyValueFactory<Disponibilite, String>("typevoiture"));

        if (txt_dateArrivee.getText().equals("") | txt_dateSortie.getText().equals("") | txt_dateArrivee.getText().equals("") && txt_dateSortie.getText().equals("")) {
            listM = new Mysqlconnect().getDataPlace();
        } else {
            listM = new Mysqlconnect().getDataPlaceByDate(txt_dateArrivee.getText(), txt_dateSortie.getText());
        }
        table_disponibilite.setItems(listM);
    }

    @FXML
    void search_dispo() {
        col_id.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<Disponibilite, Integer>("numParking"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<Disponibilite, String>("categorie"));
        col_typeVoiture.setCellValueFactory(new PropertyValueFactory<Disponibilite, String>("typevoiture"));

        dataList = new Mysqlconnect().getDataPlace();

        table_disponibilite.setItems(dataList);

        FilteredList<Disponibilite> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(place -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(place.getEtage()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches password
                } else // Does not match.
                    if (place.getTypevoiture().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches password
                    } else
                        return place.getCategorie().toLowerCase().contains(lowerCaseFilter); // Filter matches password
            });
        });

        SortedList<Disponibilite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_disponibilite.comparatorProperty());
        table_disponibilite.setItems(sortedData);
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

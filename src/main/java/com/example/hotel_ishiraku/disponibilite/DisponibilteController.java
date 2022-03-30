package com.example.hotel_ishiraku.disponibilite;

import com.example.hotel_ishiraku.mysqlconnect;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
    private TextField txt_id;

    @FXML
    private TextField txt_dateSortie;

    @FXML
    private TextField txt_dateArrivee;

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

    public void Dispo() {
        conn = mysqlconnect.ConnectDb();
        String sql = "SELECT p.id, p.etage, p.numParking, cat.categorie, t.typevoiture " +
                "from ishiraku_place p, ishiraku_categorie cat, ishiraku_typevoiture t " +
                "where p.categorie=cat.id and p.typevoiture=t.id_type and p.id not in " +
                "(SELECT place from ishiraku_reservation where (?<=dateSortie and ?>=dateEntree) " +
                "or (? >=dateEntree and ?<=dateSortie)) order by id";
        try {
            assert conn != null;

            if (txt_dateArrivee.getText().compareTo(txt_dateSortie.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Impossible que la date de début soit après la date de fin, veuillez réessayez s'il vous plaît");
            } else {

                pst = conn.prepareStatement(sql);

                pst.setString(1, txt_dateSortie.getText());
                pst.setString(2, txt_dateSortie.getText());
                pst.setString(3, txt_dateArrivee.getText());
                pst.setString(4, txt_dateArrivee.getText());

                pst.execute();
                JOptionPane.showMessageDialog(null, "Voici les disponibilités pour ces dates");
                UpdateTable();
//            }
            }
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


        if (txt_dateArrivee.getText().equals("")) {
            listM = new mysqlconnect().getDataPlace();
            System.out.println("C'est vide");
        } else {
            System.out.println("C'est pas vide" + txt_dateArrivee.getText());
        }

        table_disponibilite.setItems(listM);
    }

    @FXML
    void search_dispo() {
        col_id.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<disponibilite, Integer>("numParking"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("categorie"));
        col_typeVoiture.setCellValueFactory(new PropertyValueFactory<disponibilite, String>("typevoiture"));

        dataList = new mysqlconnect().getDataPlace();

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
                } else // Does not match.
                    if (place.getTypevoiture().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches password
                    } else
                        return place.getCategorie().toLowerCase().contains(lowerCaseFilter); // Filter matches password
            });
        });

        SortedList<disponibilite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_disponibilite.comparatorProperty());
        table_disponibilite.setItems(sortedData);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_dispo();
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

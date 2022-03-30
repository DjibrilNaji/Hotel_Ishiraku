package com.example.hotel_ishiraku.employes;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmployesController implements Initializable {

    @FXML
    private TableView<employes> table_employes;

    @FXML
    private TableColumn<employes, Integer> col_id;

    @FXML
    private TableColumn<employes, String> col_role;

    @FXML
    private TableColumn<employes, String> col_nom;

    @FXML
    private TableColumn<employes, String> col_prenom;

    @FXML
    private TableColumn<employes, String> col_login;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_role;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_login;

    @FXML
    private TextField txt_mdp;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;


    ObservableList<employes> listM;
    ObservableList<employes> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void clearEvent(ActionEvent event) {
        clear();
    }




    public void Add_employes() {
        conn = mysqlconnect.ConnectDb();
        String sql = "INSERT INTO ishiraku_employes (role, nom, prenom, login, mdp)" +
                "VALUES (?, ?, ?, ?, PASSWORD(?))";

        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_role.getText());
            pst.setString(2, txt_nom.getText());
            pst.setString(3, txt_prenom.getText());
            pst.setString(4, txt_login.getText());
            pst.setString(5, txt_mdp.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Employé ajouter avec succès");
            UpdateTable();
//            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //////// methode select lavage ///////
    @FXML
    void getSelected(MouseEvent event) {
        index = table_employes.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_role.setText(col_role.getCellData(index).toString());
        txt_nom.setText(col_nom.getCellData(index).toString());
        txt_prenom.setText(col_prenom.getCellData(index).toString());
        txt_login.setText(col_login.getCellData(index).toString());
    }

    public void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_role.getText();
            String value3 = txt_nom.getText();
            String value4 = txt_prenom.getText();
            String value5 = txt_login.getText();

            String sql = "UPDATE ishiraku_employes SET id ='" + value1 + "', role = '" + value2 + "', nom = '" + value3 + "', prenom = '" + value4 + "', " +
                    "`login` = '" + value5 + "' WHERE `id` = '" + value1 + "';";

            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Employé modifié");
            UpdateTable();
//            search_client();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from ishiraku_employes where id= ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Employé supprimé avec succès");
            UpdateTable();
//            search_client();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<employes, Integer>("id"));
        col_role.setCellValueFactory(new PropertyValueFactory<employes, String>("role"));
        col_nom.setCellValueFactory(new PropertyValueFactory<employes, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<employes, String>("prenom"));
        col_login.setCellValueFactory(new PropertyValueFactory<employes, String>("login"));

        listM = mysqlconnect.getDataEmployes();
        table_employes.setItems(listM);
    }

//    @FXML
//    void search_client() {
//        col_id.setCellValueFactory(new PropertyValueFactory<client, Integer>("id"));
//        col_nom.setCellValueFactory(new PropertyValueFactory<client, String>("nom"));
//        col_prenom.setCellValueFactory(new PropertyValueFactory<client, String>("prenom"));
//        col_numero.setCellValueFactory(new PropertyValueFactory<client, String>("numero_telephone"));
//
//        dataList = com.example.hotel_ishiraku.mysqlconnect.getDataClient();
//
//        table_client.setItems(dataList);
//
//        FilteredList<client> filteredData = new FilteredList<>(dataList, b -> true);
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(person -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches username
//                } else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches password
//                } else
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
//        search_client();
    }

    public void sommaire(ActionEvent actionEvent) throws IOException {
        btn_accueil.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotel_ishiraku/SommaireReceptionniste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    void clear() {
        txt_id.setText(null);
        txt_role.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_login.setText(null);
    }


}
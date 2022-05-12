package com.example.hotel_ishiraku.employes;

import com.example.hotel_ishiraku.DAO.EmployesDAO;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployesController implements Initializable {

    @FXML
    private TableView<Employes> table_employes;

    @FXML
    private TableColumn<Employes, Integer> col_id;

    @FXML
    private TableColumn<Employes, String> col_role;

    @FXML
    private TableColumn<Employes, String> col_nom;

    @FXML
    private TableColumn<Employes, String> col_prenom;

    @FXML
    private TableColumn<Employes, String> col_login;

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
    private PasswordField txt_mdp;

    @FXML
    private TextField filterField;

    @FXML
    private Button btn_accueil;


    ObservableList<Employes> listM;
    ObservableList<Employes> dataList;

    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        search_employes();
    }

    @FXML
    void clear() {
        txt_id.setText(null);
        txt_role.setText(null);
        txt_nom.setText(null);
        txt_prenom.setText(null);
        txt_login.setText(null);
        txt_mdp.setText(null);
    }

    public void add() {
        Employes employes = new Employes(txt_role.getText(), txt_nom.getText(), txt_prenom.getText(), txt_login.getText(), txt_mdp.getText());
        new EmployesDAO().addEmployes(employes);
        updateTable();
        clear();
    }

    public void edit() {
        Employes employes = new Employes(txt_role.getText(), txt_nom.getText(), txt_prenom.getText(), txt_login.getText(), txt_mdp.getText());
        new EmployesDAO().editEmployes(employes);
        updateTable();
        clear();
    }

    public void delete() {
        Employes employes = new Employes(Integer.parseInt(txt_id.getText()));
        new EmployesDAO().deleteEmployes(employes);
        updateTable();
        clear();
    }

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

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Employes, Integer>("id"));
        col_role.setCellValueFactory(new PropertyValueFactory<Employes, String>("role"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Employes, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Employes, String>("prenom"));
        col_login.setCellValueFactory(new PropertyValueFactory<Employes, String>("login"));

        listM = new Mysqlconnect().getDataEmployes();
        table_employes.setItems(listM);
    }

    @FXML
    public void search_employes() {
        col_id.setCellValueFactory(new PropertyValueFactory<Employes, Integer>("id"));
        col_role.setCellValueFactory(new PropertyValueFactory<Employes, String>("role"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Employes, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Employes, String>("prenom"));
        col_login.setCellValueFactory(new PropertyValueFactory<Employes, String>("login"));

        dataList = new Mysqlconnect().getDataEmployes();

        table_employes.setItems(dataList);

        FilteredList<Employes> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<Employes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_employes.comparatorProperty());
        table_employes.setItems(sortedData);
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
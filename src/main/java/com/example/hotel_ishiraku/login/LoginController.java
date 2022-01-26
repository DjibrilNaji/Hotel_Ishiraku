package com.example.hotel_ishiraku.login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.hotel_ishiraku.mysqlconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane pane_login;

    @FXML
    private TextField txt_login;

    @FXML
    private PasswordField txt_mdp;

    @FXML
    private ComboBox role;

    @FXML
    private Button btn_login;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void LoginpaneShow() {
        pane_login.setVisible(true);
    }

    @FXML
    private void Login(ActionEvent event) throws Exception {
        conn = mysqlconnect.ConnectDb();
        String sql = "Select * from employes where role = ? and login = ? and mdp = ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, role.getValue().toString());
            pst.setString(2, txt_login.getText());
            pst.setString(3, txt_mdp.getText());

            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Connexion réussie");

                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            } else
                JOptionPane.showMessageDialog(null, "Identifiant ou login incorrect");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll("receptionniste", "laveur");
    }


}
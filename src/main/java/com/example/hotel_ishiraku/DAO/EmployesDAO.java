package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployesDAO {

    Connection conn = new Mysqlconnect().ConnectDb();

    public void Add_employes(String role, String nom, String prenom, String login, String mdp) {
        String sql = "INSERT INTO ishiraku_employes (role, nom, prenom, login, mdp)" +
                "VALUES (?, ?, ?, ?, PASSWORD(?))";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, role);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setString(4, login);
            pst.setString(5, mdp);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Employé ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Edit_employes(String id, String role, String nom, String prenom, String login) {
        String sql = "UPDATE ishiraku_employes SET id =?, role =?, nom =?, prenom = ?, `login` = ? WHERE `id` = ?;";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.setString(2, role);
            pst.setString(3, nom);
            pst.setString(4, prenom);
            pst.setString(5, login);
            pst.setString(6, id);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Employé modifié");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete_employes(String id) {
        conn = new Mysqlconnect().ConnectDb();
        String sql = "delete from ishiraku_employes where id= ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Employé supprimé avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}

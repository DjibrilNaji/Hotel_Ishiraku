package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;
import com.example.hotel_ishiraku.employes.Employes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployesDAO {

    Connection conn = new Mysqlconnect().connectDb();

    public void addEmployes(Employes employes) {
        String sql = "INSERT INTO ishiraku_employes (role, nom, prenom, login, mdp)" +
                "VALUES (?, ?, ?, ?, PASSWORD(?))";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, employes.getRole());
            pst.setString(2, employes.getNom());
            pst.setString(3, employes.getPrenom());
            pst.setString(4, employes.getLogin());
            pst.setString(5, employes.getMdp());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Employé ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editEmployes(Employes employes) {
        String sql = "UPDATE ishiraku_employes SET id =?, role =?, nom =?, prenom = ?, `login` = ? WHERE `id` = ?;";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, employes.getId());
            pst.setString(2, employes.getRole());
            pst.setString(3, employes.getNom());
            pst.setString(4, employes.getPrenom());
            pst.setString(5, employes.getLogin());
            pst.setInt(6, employes.getId());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Employé modifié");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteEmployes(Employes employes) {
        conn = new Mysqlconnect().connectDb();
        String sql = "delete from ishiraku_employes where id= ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, employes.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Employé supprimé avec succès");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}

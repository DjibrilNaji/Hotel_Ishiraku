package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LavageDAO {

    Connection conn = new Mysqlconnect().ConnectDb();

    public void Add_lavage(String idLaveur, String date, String heure, String voiture, String commentaire) {
        String sql = "insert into ishiraku_lavage(laveur,date, heure,voiture,commentaire) values (?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, idLaveur);
            pst.setString(2, date);
            pst.setString(3, heure);
            pst.setString(4, voiture);
            pst.setString(5, commentaire);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Lavage ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Edit_lavage(String id, String laveur, String date, String heure, String voiture, String commentaire) {
        String sql = "update ishiraku_lavage set id= ?, laveur= ?,date= ?,heure= ?,voiture= ?,commentaire= ? where id = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.setString(2, laveur);
            pst.setString(3, date);
            pst.setString(4, heure);
            pst.setString(5, voiture);
            pst.setString(6, commentaire);
            pst.setString(7, id);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(String id) {
        String sql = "delete from ishiraku_lavage where id= ? ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}

package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;
import com.example.hotel_ishiraku.lavage.Lavage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LavageDAO {

    Connection conn = new Mysqlconnect().connectDb();

    public void addLavage(Lavage lavage) {
        String sql = "insert into ishiraku_lavage(laveur,date, heure,voiture,commentaire) values (?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, lavage.getLaveur());
            pst.setString(2, lavage.getDate());
            pst.setString(3, lavage.getHeure());
            pst.setString(4, lavage.getVoiture());
            pst.setString(5, lavage.getCommentaire());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Lavage ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editLavage(Lavage lavage) {
        String sql = "update ishiraku_lavage set id= ?, laveur= ?,date= ?,heure= ?,voiture= ?,commentaire= ? where id = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, lavage.getId());
            pst.setInt(2, lavage.getLaveur());
            pst.setString(3, lavage.getDate());
            pst.setString(4, lavage.getHeure());
            pst.setString(5, lavage.getVoiture());
            pst.setString(6, lavage.getCommentaire());
            pst.setInt(7, lavage.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void delete(Lavage lavage) {
        String sql = "delete from ishiraku_lavage where id= ? ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, lavage.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}

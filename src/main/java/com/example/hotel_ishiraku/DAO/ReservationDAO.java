package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationDAO {

    Connection conn = new Mysqlconnect().ConnectDb();

    public void Add_reservation(String id, String dateArrivee, String dateSortie, String place) {
        String sql = "INSERT INTO `ishiraku_reservation` (`client`, `dateEntree`, `dateSortie`, `place`) VALUES (?,?,?,?);";
        String sql2 = "UPDATE ishiraku_place SET id_client = ? WHERE id = ? ;";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            PreparedStatement pst2 = conn.prepareStatement(sql2);

            pst.setString(1, id);
            pst.setString(2, dateArrivee);
            pst.setString(3, dateSortie);
            pst.setString(4, place);

            pst2.setString(1, id);
            pst2.setString(2, place);

            pst.execute();
            pst2.execute();

            JOptionPane.showMessageDialog(null, "Reservation ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete_reservation(String place) {
        String sql = "delete from ishiraku_reservation where place = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, place);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Edit_reservation(String id, String dateArrivee, String dateSortie, String place) {
        String sql = "UPDATE `ishiraku_reservation` SET client = ?, dateEntree = ?,dateSortie = ? ,place = ? where place = place";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.setString(2, dateArrivee);
            pst.setString(3, dateSortie);
            pst.setString(4, place);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}

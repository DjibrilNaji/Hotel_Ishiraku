package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;
import com.example.hotel_ishiraku.reservation.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationDAO {

    Connection conn = new Mysqlconnect().connectDb();

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO `ishiraku_reservation` (`client`, `dateEntree`, `dateSortie`, `place`) VALUES (?,?,?,?);";
        String sql2 = "UPDATE ishiraku_place SET id_client = ? WHERE id = ? ;";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            PreparedStatement pst2 = conn.prepareStatement(sql2);

            pst.setInt(1, reservation.getClient());
            pst.setString(2, reservation.getDateEntree());
            pst.setString(3, reservation.getDateSortie());
            pst.setInt(4, reservation.getPlace());

            pst.setInt(1, reservation.getClient());
            pst.setInt(2, reservation.getPlace());

            pst.execute();
            pst2.execute();

            JOptionPane.showMessageDialog(null, "Reservation ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editReservation(Reservation reservation) {
        String sql = "UPDATE `ishiraku_reservation` SET client = ?, dateEntree = ?,dateSortie = ? ,place = ? where place = place";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, reservation.getClient());
            pst.setString(2, reservation.getDateEntree());
            pst.setString(3, reservation.getDateSortie());
            pst.setInt(4, reservation.getPlace());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void deleteReservation(Reservation reservation) {
        String sql = "delete from ishiraku_reservation where place = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, reservation.getPlace());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Supprimer avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


}

package com.example.hotel_ishiraku;

import com.example.hotel_ishiraku.client.client;
import com.example.hotel_ishiraku.disponibilite.disponibilite;
import com.example.hotel_ishiraku.lavage.lavage;
import com.example.hotel_ishiraku.reservation.reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class mysqlconnect {

    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://sio-hautil.eu/najid", "najid", "Djibs785");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<lavage> getDataLavage() {
        Connection conn = ConnectDb();
        ObservableList<lavage> listLavage = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_lavage");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLavage.add(new lavage(rs.getInt("id"), rs.getInt("laveur"), rs.getString("date"), rs.getString("heure"), rs.getString("voiture"), rs.getString("commentaire")));
            }
        } catch (Exception ignored) {
        }
        return listLavage;
    }

    public static ObservableList<disponibilite> getDataPlace() {
        Connection conn = ConnectDb();
        ObservableList<disponibilite> listPlace = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select p.id, p.etage, p.numParking, p.id_client, c.nom, cat.categorie, t.typevoiture\n" +
                    "from ishiraku_place p, ishiraku_client c, ishiraku_categorie cat, ishiraku_typevoiture t\n" +
                    "where p.id_client=c.id and p.categorie=cat.id and p.typevoiture=t.id_type ORDER BY id ");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                listPlace.add(new disponibilite(rs.getInt("id"),
                        rs.getInt("etage"),
                        rs.getInt("numParking"),
                        rs.getString("categorie"),
                        rs.getString("typevoiture"),
                        rs.getInt("id_client"),
                        rs.getString("nom")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listPlace;
    }

    public static ObservableList<client> getDataClient() {
        Connection conn = ConnectDb();
        ObservableList<client> listClient = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listClient.add(new client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("numero_telephone")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listClient;
    }

    public static ObservableList<reservation> getDataReservation() {
        Connection conn = ConnectDb();
        ObservableList<reservation> listReservation = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_reservation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listReservation.add(new reservation(
                        rs.getInt("client"),
                        rs.getString("dateEntree"),
                        rs.getString("dateSortie"),
                        rs.getInt("place")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listReservation;
    }

}



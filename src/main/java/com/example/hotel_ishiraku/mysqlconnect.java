package com.example.hotel_ishiraku;

import com.example.hotel_ishiraku.client.client;
import com.example.hotel_ishiraku.disponibilite.disponibilite;
import com.example.hotel_ishiraku.employes.employes;
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

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelishiraku", "root", "root");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La connexion à la base de données a échoué. Veuillez réessayer dans quelques minutes, ou relancez votre connexion");
            return null;
        }
    }

    public static ObservableList<lavage> getDataLavage() {
        Connection conn = ConnectDb();
        ObservableList<lavage> listLavage = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select l.id, e.prenom, l.date, l.heure, l.voiture, l.commentaire\n" +
                    "from ishiraku_lavage l, ishiraku_employes e\n" +
                    "where l.laveur=e.id ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLavage.add(new lavage(rs.getInt("id"),
                        rs.getString("prenom"),
                        rs.getString("date"),
                        rs.getString("heure"),
                        rs.getString("voiture"),
                        rs.getString("commentaire")));
            }
        } catch (Exception ignored) {

        }
        return listLavage;
    }

    public ObservableList<disponibilite> getDataPlace() {
        Connection conn = ConnectDb();
        ObservableList<disponibilite> listPlace = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select p.id, p.etage, p.numParking, cat.categorie, t.typevoiture\n" +
                    "from ishiraku_place p, ishiraku_categorie cat, ishiraku_typevoiture t\n" +
                    "where p.categorie=cat.id and p.typevoiture=t.id_type ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listPlace.add(new disponibilite(rs.getInt("id"),
                        rs.getInt("etage"),
                        rs.getInt("numParking"),
                        rs.getString("categorie"),
                        rs.getString("typevoiture")));
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

    public static ObservableList<employes> getDataEmployes() {
        Connection conn = ConnectDb();
        ObservableList<employes> listEmployes = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_employes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listEmployes.add(new employes(
                        rs.getInt("id"),
                        rs.getString("role"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("login")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listEmployes;
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



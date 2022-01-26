package com.example.hotel_ishiraku;

//import com.example.hotel_ishiraku.client.client.client;
//import com.example.hotel_ishiraku.lavage.lavage.lavage;

import com.example.hotel_ishiraku.client.client;
import com.example.hotel_ishiraku.disponibilite.disponibilite;
import com.example.hotel_ishiraku.lavage.lavage;
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelishiraku", "root", "root");

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
            PreparedStatement ps = conn.prepareStatement("select * from lavage");
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
            PreparedStatement ps = conn.prepareStatement("select * from place");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listPlace.add(new disponibilite(rs.getInt("id"),
                        rs.getInt("etage"),
                        rs.getInt("numParking"),
                        rs.getString("disponibilite"),
                        rs.getInt("categorie"),
                        rs.getInt("typevoiture"),
                        rs.getInt("client")));
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
            PreparedStatement ps = conn.prepareStatement("select * from client");
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


}



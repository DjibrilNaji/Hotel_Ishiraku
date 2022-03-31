package com.example.hotel_ishiraku;

import com.example.hotel_ishiraku.client.Client;
import com.example.hotel_ishiraku.disponibilite.Disponibilite;
import com.example.hotel_ishiraku.employes.Employes;
import com.example.hotel_ishiraku.lavage.Lavage;
import com.example.hotel_ishiraku.reservation.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Mysqlconnect {

    public Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelishiraku", "root", "root");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La connexion à la base de données a échoué. Veuillez réessayer dans quelques minutes, ou relancez votre connexion");
            return null;
        }
    }

    public ObservableList<Lavage> getDataLavage() {
        Connection conn = ConnectDb();
        ObservableList<Lavage> listLavage = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select l.id, l.laveur, e.prenom, l.date, l.heure, l.voiture, l.commentaire " +
                    "from ishiraku_lavage l, ishiraku_employes e where l.laveur=e.id ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLavage.add(new Lavage(rs.getInt("id"),
                        rs.getInt("laveur"),
                        rs.getString("prenom"),
                        rs.getString("date"),
                        rs.getString("heure"),
                        rs.getString("voiture"),
                        rs.getString("commentaire")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listLavage;
    }

    public ObservableList<Disponibilite> getDataPlace() {
        Connection conn = ConnectDb();
        ObservableList<Disponibilite> listPlace = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select p.id, p.etage, p.numParking, cat.categorie, t.typevoiture\n" +
                    "from ishiraku_place p, ishiraku_categorie cat, ishiraku_typevoiture t\n" +
                    "where p.categorie=cat.id and p.typevoiture=t.id_type ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listPlace.add(new Disponibilite(rs.getInt("id"),
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

    public ObservableList<Disponibilite> getDataPlaceByDate(String dateArrivee, String dateSortie) {
        Connection conn = ConnectDb();
        ObservableList<Disponibilite> listPlace = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("SELECT p.id, p.etage, p.numParking, cat.categorie, t.typevoiture " +
                    "from ishiraku_place p, ishiraku_categorie cat, ishiraku_typevoiture t " +
                    "where p.categorie=cat.id and p.typevoiture=t.id_type and p.id not in (SELECT place from ishiraku_reservation " +
                    "where (?<=dateSortie and ?>=dateEntree) or (? >=dateEntree and ?<=dateSortie)) order by id");

            ps.setString(1, dateSortie);
            ps.setString(2, dateSortie);
            ps.setString(3, dateArrivee);
            ps.setString(4, dateArrivee);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listPlace.add(new Disponibilite(rs.getInt("id"),
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

    public ObservableList<Client> getDataClient() {
        Connection conn = ConnectDb();
        ObservableList<Client> listClient = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listClient.add(new Client(
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

    public ObservableList<Employes> getDataEmployes() {
        Connection conn = ConnectDb();
        ObservableList<Employes> listEmployes = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_employes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listEmployes.add(new Employes(
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


    public ObservableList<Reservation> getDataReservation() {
        Connection conn = ConnectDb();
        ObservableList<Reservation> listReservation = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from ishiraku_reservation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listReservation.add(new Reservation(
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



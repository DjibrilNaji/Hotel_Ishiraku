package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;
import com.example.hotel_ishiraku.client.Client;
import com.example.hotel_ishiraku.employes.Employes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientDAO {

    Connection conn = new Mysqlconnect().connectDb();

    // mettre l'objet client
    public void addClient(Client client) {
        new Mysqlconnect().connectDb();
        String sql = "insert into ishiraku_client(nom,prenom,numero_telephone) values (?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, client.getNom());
            pst.setString(2, client.getPrenom());
            pst.setString(3, client.getNumero_telephone());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Client ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteClient(Client client) {
        conn = new Mysqlconnect().connectDb();
        String sql = "delete from ishiraku_client where id= ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, client.getId());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Client supprimé avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editClient(Client client) {
        new Mysqlconnect().connectDb();
        String sql = "update ishiraku_client set nom= ?,prenom= ?,numero_telephone=? where id= ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, client.getNom());
            pst.setString(2, client.getPrenom());
            pst.setString(3, client.getNumero_telephone());
            pst.setInt(4, client.getId());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Client modifié");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }



}

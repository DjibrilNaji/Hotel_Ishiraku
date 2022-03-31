package com.example.hotel_ishiraku.DAO;

import com.example.hotel_ishiraku.Mysqlconnect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientDAO {

    Connection conn = new Mysqlconnect().ConnectDb();

    public void Add_client(String nom, String prenom, String numero) {
        new Mysqlconnect().ConnectDb();
        String sql = "insert into ishiraku_client(nom,prenom,numero_telephone) values (?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, numero);

            pst.execute();

            JOptionPane.showMessageDialog(null, "Client ajouter avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void Edit_client(String id, String nom, String prenom, String numero) {
        try {
            conn = new Mysqlconnect().ConnectDb();

            String sql = "update ishiraku_client set nom= ?,prenom= ?,numero_telephone=? where id= ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, numero);
            pst.setString(4, id);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Client modifié");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Delete_client(String id) {
        new Mysqlconnect().ConnectDb();
        String sql = "delete from ishiraku_client where id= ? ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Client supprimé avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }




}

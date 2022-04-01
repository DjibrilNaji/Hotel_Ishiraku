package com.example.hotel_ishiraku.DAO;
import javax.swing.*;

public class DisponibiliteDAO {

    public void dispoPlace(String dateArrivee, String dateSortie) {
        if (dateArrivee.compareTo(dateSortie) > 0) {
            JOptionPane.showMessageDialog(null, "Impossible que la date de début soit après la date de fin, veuillez réessayez s'il vous plaît");
        } else {
            JOptionPane.showMessageDialog(null, "Voici les disponibilités pour ces dates");
        }
    }
}

package com.example.hotel_ishiraku.lavage;


public class lavage {

    int id;
    String prenom, date, heure, voiture, commentaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public lavage(int id, String prenom, String date, String heure, String voiture, String commentaire) {
        this.id = id;
        this.prenom = prenom;
        this.date = date;
        this.heure = heure;
        this.voiture = voiture;
        this.commentaire = commentaire;
    }

}


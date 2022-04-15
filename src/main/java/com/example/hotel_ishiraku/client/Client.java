package com.example.hotel_ishiraku.client;

public class Client {

    int id;
    String nom, prenom, numero_telephone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public Client(int id, String nom, String prenom, String numero_telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero_telephone = numero_telephone;
    }

    public Client(String nom, String prenom, String numero_telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero_telephone = numero_telephone;
    }

    public Client(int id) {
        this.id = id;
    }

}

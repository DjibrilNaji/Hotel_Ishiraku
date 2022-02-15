package com.example.hotel_ishiraku.disponibilite;

public class disponibilite {

    int id, etage, numParking, categorie, typevoiture, id_client;
    String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNumParking() {
        return numParking;
    }

    public void setNumParking(int numParking) {
        this.numParking = numParking;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getTypevoiture() {
        return typevoiture;
    }

    public void setTypevoiture(int typevoiture) {
        this.typevoiture = typevoiture;
    }

    public String getNom() {
        return nom;
    }

    public void setNon(String nom) {
        this.nom = nom;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public disponibilite(int id, int etage, int numParking, int categorie, int typevoiture, int id_client, String nom) {
        this.id = id;
        this.etage = etage;
        this.numParking = numParking;
        this.categorie = categorie;
        this.typevoiture = typevoiture;
        this.id_client = id_client;
        this.nom = nom;
    }

}

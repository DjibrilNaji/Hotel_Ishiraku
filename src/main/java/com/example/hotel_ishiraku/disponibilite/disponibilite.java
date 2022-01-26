package com.example.hotel_ishiraku.disponibilite;

public class disponibilite {

    int id, etage, numParking, categorie, typevoiture, client;
    String disponibilite;

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

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public disponibilite(int id, int etage, int numParking, String disponibilite, int categorie, int typevoiture, int client) {
        this.id = id;
        this.etage = etage;
        this.numParking = numParking;
        this.categorie = categorie;
        this.typevoiture = typevoiture;
        this.client = client;
        this.disponibilite = disponibilite;
    }

}

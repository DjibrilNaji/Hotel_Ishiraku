package com.example.hotel_ishiraku.disponibilite;

public class Disponibilite {

    int id, etage, numParking;
    String categorie, typevoiture;

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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTypevoiture() {
        return typevoiture;
    }

    public void setTypevoiture(String typevoiture) {
        this.typevoiture = typevoiture;
    }

    public Disponibilite(int id, int etage, int numParking, String categorie, String typevoiture) {
        this.id = id;
        this.etage = etage;
        this.numParking = numParking;
        this.categorie = categorie;
        this.typevoiture = typevoiture;

    }

}

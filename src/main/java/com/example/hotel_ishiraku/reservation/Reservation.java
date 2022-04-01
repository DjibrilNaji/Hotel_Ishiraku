package com.example.hotel_ishiraku.reservation;

public class Reservation {
    int client, place;
    String dateEntree, dateSortie;

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(String dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Reservation(int client, String dateEntree, String dateSortie, int place) {
        this.client = client;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.place = place;
    }

    public Reservation(int place) {
        this.place = place;
    }
}

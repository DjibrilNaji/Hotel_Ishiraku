package com.example.hotel_ishiraku.reservation_place;

        import java.util.Date;

        public class reservation_place {

        int client, place;
        Date dateEntree, dateSortie;

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

        public Date getDateEntree() {
        return dateEntree;
        }

        public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
        }

        public Date getDateSortie() {
        return dateSortie;
        }

        public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
        }

        public reservation_place(int client, int place, Date dateEntree, Date dateSortie) {
        this.client = client;
        this.place = place;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        }
        }

package com.example.hotel_ishiraku.employes;

public class Employes {

    int id;
    String role, nom, prenom, login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Employes(int id, String role, String nom, String prenom, String login) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }
}

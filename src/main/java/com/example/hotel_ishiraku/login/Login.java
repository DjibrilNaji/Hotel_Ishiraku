package com.example.hotel_ishiraku.login;

public class Login {

    String role, login, mdp;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Login(String role, String login, String mdp) {
        this.role = role;
        this.login = login;
        this.mdp = mdp;
    }
}

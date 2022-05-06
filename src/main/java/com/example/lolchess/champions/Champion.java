package com.example.lolchess.champions;

public abstract class Champion {
    protected String nom;
    protected int vie;


    protected Champion(String nom, int vie) {
        this.nom = nom;
        this.vie = vie;
    }

    public String getNom() {
        return this.nom;
    }
}

package com.example.lolchess.champions;

public abstract class Champion {
    protected String nom;
    protected int vie;
    protected int equipe;


    protected Champion(String nom, int vie, int equipe) {
        this.nom = nom;
        this.vie = vie;
        this.equipe = equipe;
    }

    public String getNom() {
        return this.nom;
    }
}

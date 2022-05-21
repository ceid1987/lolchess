package com.example.lolchess.controleur;

public class ControleurCreerChampion {
    protected Liste_Champions liste_Champions;
    protected StockEtreVivant stockEtreVivant;
    public ControleurCreerProtagoniste(StockEtreVivant stock) {
        stockEtreVivant = stock;
    }
    public void creerEtreVivant(TypeEtreVivant typeEtreVivant, String nom) {
        switch(typeEtreVivant) {
            case HOMME:
                Homme homme = new Homme(nom);
                stockEtreVivant.ajouterHomme(homme);
                break;
            case HEROS:
                Heros heros = new Heros(nom);
                stockEtreVivant.ajouterHeros(heros);
                break;
            case DRAGON:
                Dragon dragon = new Dragon(nom);
                stockEtreVivant.ajouterDragon(dragon);
                break;
        }
    }

}

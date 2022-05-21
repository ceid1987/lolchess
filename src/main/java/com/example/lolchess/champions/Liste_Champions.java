package com.example.lolchess.champions;
import java.util.ArrayList;
import java.util.List;

public class Liste_Champions {
    private List<TypeCombattant> combattant = new ArrayList<>();
    private List<TypeEnchanteur> enchenteur = new ArrayList<>();
    private List<TypeMage> mage = new ArrayList<>();
    private List<TypeTank> tank = new ArrayList<>();
    private List<TypeTireur> tireur = new ArrayList<>();

    public void ajouterCombattant(TypeCombattant combattant) {
        combattant.add(combattant);
    }
    public void ajouterEnchanteur(TypeEnchanteur enchenteur) {
        enchenteur.add(enchenteur);
    }
    public void ajouterMage(TypeMage mage) {
        mage.add(mage);
    }
    public void ajouterTank(TypeTank tank) {
        tank.add(tank);
    }
    public void ajouterTireur(TypeTireur tireur) {
        tireur.add(tireur);
    }

}

package com.iut.metz.bankee.back.metier.objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe M�tier Banque
 * Repr�sente la gestion de la banque
 * @author Lo�c NOEL
 */
public class Banque {
    // Attributs de la classe
    private List<Compte> comptes;
    
    // Accesseurs de la classe
    /**
     * @return the comptes
     */
    public List<Compte> getComptes() {
        return comptes;
    }
    
    /**
     * Constructeur de la classe Banque
     * @param comptes 
     */
    public Banque(List<Compte> comptes) {
        if(comptes == null) {
            this.comptes = new ArrayList<>();
        } else {
            this.comptes = comptes;
        }
    }
}

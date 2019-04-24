package metier;

import java.util.List;

/**
 * Classe Métier Banque
 * Représente la gestion de la banque
 * @author Loïc NOEL
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
    
    public Banque(List<Compte> comptes) {
        
    }
}

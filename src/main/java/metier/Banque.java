package metier;

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
    
    public Banque(List<Compte> comptes) {
        
    }
}

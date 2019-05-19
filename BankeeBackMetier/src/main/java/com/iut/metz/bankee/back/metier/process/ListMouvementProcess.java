package com.iut.metz.bankee.back.metier.process;

import com.iut.metz.bankee.back.metier.objet.Mouvement;

import java.util.List;

public class ListMouvementProcess {
    public List<Mouvement> addMouvement(List<Mouvement> listAEnrichire, List<Mouvement> mouvements) {
        if (mouvements != null) {
            mouvements.forEach( mouvement -> {
                if (mouvement != null && !contains(listAEnrichire,mouvement)) {
                    listAEnrichire.add(mouvement);
                }
            });
        }
        return listAEnrichire;
    }

    private boolean contains(List<Mouvement> mouvements, Mouvement mouvement) {
        return mouvements.stream().anyMatch(c -> c.getId()==mouvement.getId());
    }
}

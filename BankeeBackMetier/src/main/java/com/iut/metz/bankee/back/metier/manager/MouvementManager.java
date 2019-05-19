package com.iut.metz.bankee.back.metier.manager;

import com.iut.metz.bankee.back.metier.objet.Mouvement;

public class MouvementManager extends HibernateFactory<Mouvement> {
    MouvementManager() {
        super(Mouvement.class);
    }
}

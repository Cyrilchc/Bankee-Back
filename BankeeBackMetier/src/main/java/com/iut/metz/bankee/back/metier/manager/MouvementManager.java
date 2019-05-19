package com.iut.metz.bankee.back.metier.manager;

import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.Mouvement;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MouvementManager extends HibernateFactory<Mouvement> {
    private static MouvementManager instance;

    public static MouvementManager getInstance() {
        if (instance == null) {
            instance = new MouvementManager();
        }
        return instance;
    }

    MouvementManager() {
        super(Mouvement.class);
    }

    public List<Mouvement> getByCompte(Compte compte) {
        Session session = getSession();
        Query query = session.createQuery("SELECT movement from Mouvement movement where movement.compte = :compte");
        query.setParameter("compte", compte);
        return query.list();
    }
}

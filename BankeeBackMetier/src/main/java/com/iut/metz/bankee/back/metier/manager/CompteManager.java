package com.iut.metz.bankee.back.metier.manager;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.iut.metz.bankee.back.metier.objet.Compte;

public class CompteManager extends HibernateFactory<Compte> {
  private static CompteManager instance;

  private CompteManager() {
    super(Compte.class);
  }

  public static CompteManager getInstance() {
    if (instance == null) {
      instance = new CompteManager();
    }
    return instance;
  }

  public Compte getCompteByNumCompte(String numCompte) {
    AtomicReference<Compte> res = new AtomicReference<>(null);
    Session session = getSession();
    Query query = session.createQuery("from Compte as compte where compte.numeroCompte = :numCompte");
    query.setParameter("numCompte", numCompte);
    List<Compte> list = query.list();
    list.forEach(res::set);
    return res.get();
  }

  public List<Compte> getComptesByNumClient(String numClient) {
    Session session = getSession();
    Query query = session.createQuery("SELECT client.comptes from Client as client where client.numeroClient = :numClient");
    query.setParameter("numClient", numClient);
    return query.list();
  }

}

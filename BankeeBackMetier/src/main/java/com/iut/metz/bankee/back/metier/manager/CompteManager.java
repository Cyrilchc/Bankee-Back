package com.iut.metz.bankee.back.metier.manager;

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

}

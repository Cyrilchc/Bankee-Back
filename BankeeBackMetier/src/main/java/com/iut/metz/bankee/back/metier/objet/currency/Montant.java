package com.iut.metz.bankee.back.metier.objet.currency;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;

public class Montant {
  private int id;
  private double montant;
  private Monnaie monaie;

  public Montant(double montant, Monnaie monaie) {
    this(-1, montant, monaie);
  }

  public Montant(double montant) {
    this(montant, MONNAIE_PAR_DEFAUT);
  }

  public Montant(int id, double montant, Monnaie monaie) {
    this.id = id;
    this.montant = montant;
    this.monaie = monaie;
  }

  public Monnaie getMonaie() {
    return monaie;
  }

  public double getMontant() {
    return montant;
  }
}

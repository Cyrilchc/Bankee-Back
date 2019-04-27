package com.iut.metz.bankee.back.metier.objet.currency;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;

public class Montant {
  private int id;
  private double montant;
  private Monnaie monnaie;

  public Montant(double montant, Monnaie monnaie) {
    this(-1, montant, monnaie);
  }

  public Montant(double montant) {
    this(montant, MONNAIE_PAR_DEFAUT);
  }

  public Montant(int id, double montant, Monnaie monnaie) {
    this.id = id;
    this.montant = montant;
    this.monnaie = monnaie;
  }

  public Monnaie getMonnaie() {
    return monnaie;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }

  public void setMonnaie(Monnaie monnaie) {
    this.monnaie = monnaie;
  }
}

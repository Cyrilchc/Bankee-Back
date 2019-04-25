package com.iut.metz.bankee.back.metier.objet.currency;

import static com.iut.metz.bankee.back.metier.objet.currency.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;
import static com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils.MONTANT_NEGATIF;

import com.iut.metz.bankee.back.metier.objet.exception.MontantException;

public class Montant {
  private int id;
  private double montant;
  private Monnaie monaie;

  public Montant(double montant, Monnaie monaie) throws MontantException {
    this(-1, montant, monaie);
  }

  public Montant(double montant) throws MontantException {
    this(montant, MONNAIE_PAR_DEFAUT);
  }

  public Montant(int id, double montant, Monnaie monaie) throws MontantException {
    if (montant <= 0) {
      throw new MontantException(MONTANT_NEGATIF);
    }
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

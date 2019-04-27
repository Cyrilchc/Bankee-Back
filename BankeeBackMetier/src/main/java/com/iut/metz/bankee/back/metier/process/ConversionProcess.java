package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils.MONTANT_NEGATIF;
import static  com.iut.metz.bankee.back.metier.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;

/**
 * Ce process premet de faire de la conversion d'argent d'une monnaie vers de l'euro
 * depuis Euro : somme / (Valeur En Euro pour 1 de la monnaie)
 * vers   Euro : somme * (Valeur En Euro pour 1 de la monnaie)
 */
public class ConversionProcess {

  /**
   * methode permettant de convertir une somme d'argent en euro vers une autre monnaie (ex: € -> $)
   * @param somme   la somme en euro
   * @param monnaie la monaie d'arrivé
   * @return le montant converti dans la monnaie demandé
   * @throws MetierException exception relever dans le cas ou la monnaie de conversion n'est pas valid (ex: monnaie null)
   */
  public Montant fromEuro(double somme, Monnaie monnaie) throws MetierException {
    Montant montant = null;
    if (somme <= 0) {
      throw new MontantException(MONTANT_NEGATIF);
    }
    if (new MonnaieProcess().isValid(monnaie)){
      double res = somme / monnaie.getValeurEnEuro();
      montant = new Montant(res, monnaie);
    }
    return montant;
  }

  /**
   * methode permettant de convertir une somme d'argent d'un monnaie vers l'euro (ex: $ -> €)
   * @param montant le montant à convertir
   * @return le montant converti en Euro
   * @throws MetierException exception relever dans le cas ou la monnaie de conversion n'est pas valid (ex: monnaie null)
   */
  public Montant toEuro(Montant montant) throws MetierException {
    Montant montantRes = null;
    if (new MontantProcess().isValid(montant)) {
      double somme = montant.getMontant() * montant.getMonnaie().getValeurEnEuro();
      montantRes = new Montant(somme, MONNAIE_PAR_DEFAUT);
    }
    return montantRes;
  }

}

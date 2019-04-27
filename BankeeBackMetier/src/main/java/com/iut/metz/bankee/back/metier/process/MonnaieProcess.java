package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.MonnaieExceptionUtils.MONNAIE_NEGATIF;
import static com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils.*;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import joptsimple.internal.Strings;

public class MonnaieProcess extends ObjectProcess<Monnaie> {

  public boolean isValid(Monnaie monnaie) throws MetierException {
    doValid(monnaie,
            this::isValeurEnEuroNegatif,
            this::isASymbole,
            this::isANom);
    return true;
  }

  private MetierException isValeurEnEuroNegatif(Monnaie monnaie) {
    if (monnaie.getValeurEnEuro() <= 0) {
      return new MonnaieException(MONNAIE_NEGATIF);
    }
    return null;
  }

  private MetierException isASymbole(Monnaie monnaie) {
    if (Strings.isNullOrEmpty(monnaie.getSymbole())) {
      return new MonnaieException(MONNAIE_SANS_SYMBOLE);
    }
    return null;
  }

  private MetierException isANom(Monnaie monnaie) {
    if (Strings.isNullOrEmpty(monnaie.getNom())) {
      return new MonnaieException(MONNAIE_SANS_NOM);
    }
    return null;
  }
}

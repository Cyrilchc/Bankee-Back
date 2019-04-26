package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.MonnaieExceptionUtils.MONNAIE_NEGATIF;
import static com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils.*;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class MonnaieProcess extends ObjectProcess<Monnaie> {

  public boolean isValid(Monnaie monnaie) throws MetierException {
    doValid(monnaie,
            this::isValeurEnEuroNegatif);
    return true;
  }

  private MetierException isValeurEnEuroNegatif(Monnaie monnaie) {
    if (monnaie.getValeurEnEuro() <= 0) {
      return new MonnaieException(MONNAIE_NEGATIF);
    }
    return null;
  }
}

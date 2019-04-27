package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils.*;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class MontantProcess extends ObjectProcess<Montant> {

  public boolean isValid(Montant montant) throws MetierException {
    doValid(montant,
            this::isMontantNegatif,
            this::isMonnaieValid);
    return true;
  }

  private MetierException isMonnaieValid(Montant montant) {
    try {
      new MonnaieProcess().isValid(montant.getMonnaie());
      return null;
    } catch (MetierException e) {
      log.warn(e.getMessage());
      return e;
    }
  }

  private MontantException isMontantNegatif(Montant montant) {
    if (montant.getMontant() <= 0) {
      return new MontantException(MONTANT_NEGATIF);
    }
    return null;
  }
}

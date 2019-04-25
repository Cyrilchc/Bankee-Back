package metier.currency.process;

import static metier.exception.utils.MontantExceptionUtils.*;

import java.util.function.Consumer;

import metier.currency.Montant;
import metier.exception.MontantException;

public class MontantProcess {

  public boolean isValid(Montant montant) throws MontantException {
    try {
      doValid(montant,
              this::isMontantNotNull,
              this::isMontantNegatif);
    } catch (MontantException e) {
      throw e;
    }
    return true;
  }

  private void doValid(Montant montant, ExceptionConsumer<Montant>... validateurs) throws MontantException {
    for (ExceptionConsumer<Montant> validateur : validateurs) {
      MontantException e = validateur.accept(montant);
      if (e != null) {
        throw e;
      }
    }
  }

  private MontantException isMontantNotNull(Montant montant) {
    if (montant == null) {
      return new MontantException(MONTANT_NULL);
    }
    return null;
  }

  private MontantException isMontantNegatif(Montant montant) {
    if (montant.getMontant() <= 0) {
      return new MontantException(MONTANT_NEGATIF);
    }
    return null;
  }

  @FunctionalInterface
  private interface ExceptionConsumer<T> {
    MontantException accept(T t);
  }
}

package metier.curency;

import static metier.curency.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;
import static metier.exception.utils.MontantExceptionUtils.MONTANT_NEGATIF;

import metier.exception.MontantException;

public class Montant {
  private int id;
  private double montant;
  private Monnaie monaie;

  public Montant(double montant, Monnaie monaie) throws MontantException {
    if (montant <= 0) {
      throw new MontantException(MONTANT_NEGATIF);
    }
    id = -1;
    this.montant = montant;
    this.monaie = monaie;
  }

  public Montant(double montant) {
    id = -1;
    this.montant = montant;
    this.monaie = MONNAIE_PAR_DEFAUT;
  }

  public Montant(int id, double montant, Monnaie monaie) {
    this.id = id;
    this.montant = montant;
    this.monaie = monaie;
  }

  public Monnaie getMonaie() {
    return monaie;
  }


}

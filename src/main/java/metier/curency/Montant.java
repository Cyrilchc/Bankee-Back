package metier.curency;

import static metier.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;

public class Montant {
  private int id;
  private double montant;
  private Monnaie monaie;

  public Montant(double montant, Monnaie monaie) {
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

package metier;

import static metier.exception.utils.MontantExceptionUtils.MONTANT_NULL;

import metier.curency.Montant;
import metier.exception.MontantException;

public abstract class Compte {
  private int id;
  private double solde;
  private String numeroCompte;

  public Compte(int id, double solde, String numeroCompte) {
    this.id = id;
    this.solde = solde;
    this.numeroCompte = numeroCompte;
  }

  public Compte(double solde, String numeroCompte) {
    this.id=-1;
    this.solde = solde;
    this.numeroCompte = numeroCompte;
  }

  public Compte(String numeroCompte) {
    this.id=-1;
    this.solde = 0;
    this.numeroCompte = numeroCompte;
  }

  /**
   * le solde est en euro
   * @return le solde actuel du compte
   */
  public double getSolde() {
    return solde;
  }

  public String getNumeroCompte() {
    return numeroCompte;
  }

  public abstract boolean debiter(Montant montant);

//  public boolean crediter(Montant montant) throws MontantException {
//    if (montant == null) {
//      throw new MontantException(MONTANT_NULL);
//    }
//    solde += 
//    return true;
//  }

}

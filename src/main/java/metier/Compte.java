package metier;

import metier.currency.Montant;
import metier.currency.process.MontantProcess;
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

  public void setSolde(double solde) throws MontantException {
    this.solde = solde;
  }

  public String getNumeroCompte() {
    return numeroCompte;
  }

  public boolean debiter(Montant montant) throws MontantException {
    if (new MontantProcess().isValid(montant)) {
      doDebiter(montant);
    }
    return true;
  }

  protected abstract void doDebiter(Montant montant) throws MontantException;

  public boolean crediter(Montant montant) throws MontantException {
    if (new MontantProcess().isValid(montant)) {
      solde += (montant.getMontant() * montant.getMonaie().getValeurEnEuro());
    }
    return true;
  }
}

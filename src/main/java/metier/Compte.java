package metier;

import metier.curency.Montant;

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

  public abstract boolean debiter(Montant montant);

  public boolean crediter(Montant montant) {
    return true;
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
}

package com.iut.metz.bankee.back.metier.objet;

import javax.persistence.*;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.process.MontantProcess;

@MappedSuperclass
@Table(name = "compte")
public abstract class Compte {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "solde")
  private double solde;
  @Column(name = "numero_compte")
  private String numeroCompte;

  public Compte(int id, double solde, String numeroCompte) {
    this.id = id;
    this.solde = solde;
    this.numeroCompte = numeroCompte;
  }

  public Compte(double solde, String numeroCompte) {
    this(-1, solde, numeroCompte);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setNumeroCompte(String numeroCompte) {
    this.numeroCompte = numeroCompte;
  }

  public boolean debiter(Montant montant) throws MetierException {
    if (new MontantProcess().isValid(montant)) {
      doDebiter(montant);
    }
    return true;
  }

  protected abstract void doDebiter(Montant montant) throws MontantException;

  public boolean crediter(Montant montant) throws MetierException{
    if (new MontantProcess().isValid(montant)) {
      solde += (montant.getMontant() * montant.getMonaie().getValeurEnEuro());
    }
    return true;
  }
}

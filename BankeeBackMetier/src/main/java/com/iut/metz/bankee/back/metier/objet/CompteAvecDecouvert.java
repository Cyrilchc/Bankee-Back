package com.iut.metz.bankee.back.metier.objet;

import java.util.Objects;

import javax.persistence.*;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

@Entity
public class CompteAvecDecouvert extends Compte {

  @Column(name = "decouvert_autorise")
  private double decouvertAutorise;

  public CompteAvecDecouvert(int id, double solde, String numeroCompte, double decouvertAutorise) {
    super(id, solde, numeroCompte);
    this.decouvertAutorise = decouvertAutorise;
  }


  @Override
  public void setSolde(double solde) throws MontantException {
    if (solde < -decouvertAutorise) {
      throw new MontantException(MontantExceptionUtils.SOLDE_NEGATIF);
    }
    super.setSolde(solde);
  }

  public double getDecouvertAutorise() {
    return decouvertAutorise;
  }

  public void setDecouvertAutorise(double decouvertAutorise) {
    this.decouvertAutorise = decouvertAutorise;
  }

  @Override
  protected void doDebiter(Montant montant) throws MontantException {
    double montantEnEuro = montant.getMontant()*montant.getMonnaie().getValeurEnEuro();
    setSolde(getSolde()- montantEnEuro);
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), decouvertAutorise);
  }
}

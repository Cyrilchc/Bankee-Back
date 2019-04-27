package com.iut.metz.bankee.back.metier.objet;

import javax.persistence.Entity;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

@Entity
public class CompteSansDecouvert extends Compte {
  public CompteSansDecouvert(int id, double solde, String numeroCompte) {
    super(id, solde, numeroCompte);
  }

  @Override
  public void setSolde(double solde) throws MontantException {
    if (solde < 0) {
      throw new MontantException(MontantExceptionUtils.SOLDE_NEGATIF);
    }
    super.setSolde(solde);
  }

  @Override
  protected void doDebiter(Montant montant) throws MontantException {
    setSolde(getSolde() - (montant.getMontant() * montant.getMonnaie().getValeurEnEuro()));
  }
}

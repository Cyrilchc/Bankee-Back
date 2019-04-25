package com.iut.metz.bankee.back.metier.objet;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

public class CompteSansDecouvert extends Compte {
  public CompteSansDecouvert(int id, double solde, String numeroCompte) {
    super(id, solde, numeroCompte);
  }

  public CompteSansDecouvert(double solde, String numeroCompte) {
    super(solde, numeroCompte);
  }

  public CompteSansDecouvert(String numeroCompte) {
    super(numeroCompte);
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
    double montantEnEuro = montant.getMontant()*montant.getMonaie().getValeurEnEuro();
    setSolde(getSolde()- montantEnEuro);
  }
}

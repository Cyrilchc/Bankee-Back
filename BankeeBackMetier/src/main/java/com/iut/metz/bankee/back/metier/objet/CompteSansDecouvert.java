package com.iut.metz.bankee.back.metier.objet;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("no")
public class CompteSansDecouvert extends Compte implements Serializable {
  public CompteSansDecouvert(int id, double solde, String numeroCompte, List<Mouvement> mouvements) {
    super(id, solde, numeroCompte, mouvements);
  }

    public CompteSansDecouvert() {}

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


  @Override
  public String toString() {
    return "CompteSansDecouvert{} " + super.toString();
  }
}

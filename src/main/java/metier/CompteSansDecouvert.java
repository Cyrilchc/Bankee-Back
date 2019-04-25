package metier;

import static metier.exception.utils.MontantExceptionUtils.SOLDE_NEGATIF;

import metier.currency.Montant;
import metier.exception.MontantException;

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
      throw new MontantException(SOLDE_NEGATIF);
    }
    super.setSolde(solde);
  }

  @Override
  protected void doDebiter(Montant montant) throws MontantException {
    double montantEnEuro = montant.getMontant()*montant.getMonaie().getValeurEnEuro();
    setSolde(getSolde()- montantEnEuro);
  }
}

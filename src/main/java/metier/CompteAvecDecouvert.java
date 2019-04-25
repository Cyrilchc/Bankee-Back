package metier;

import metier.currency.Montant;
import metier.exception.MontantException;

public class CompteAvecDecouvert extends Compte {
  public CompteAvecDecouvert(int id, double solde, String numeroCompte) {
    super(id, solde, numeroCompte);
  }

  public CompteAvecDecouvert(double solde, String numeroCompte) {
    super(solde, numeroCompte);
  }

  public CompteAvecDecouvert(String numeroCompte) {
    super(numeroCompte);
  }

  @Override
  protected void doDebiter(Montant montant) throws MontantException {
    setSolde(getSolde()- (montant.getMontant() * montant.getMonaie().getValeurEnEuro()));
  }
}

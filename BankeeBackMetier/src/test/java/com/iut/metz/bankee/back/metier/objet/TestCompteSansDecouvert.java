package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.DOLLARD;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class TestCompteSansDecouvert {
  private Compte compte;
  private static final double SOMME_DE_BASE = 200;

  @Before
  public void init() {
    compte = new CompteBuilder()
            .addNumeroCompte("test")
            .addSolde(SOMME_DE_BASE).build();
  }

  @Test
  public void testCompteDebiter_casEntier() throws MetierException {
    //give
    Montant montantADediter = new Montant(100);
    double expected = SOMME_DE_BASE - montantADediter.getMontant();
    //when
    compte.debiter(montantADediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test
  public void testCompteDebiter_casDouble() throws MetierException {
    //give
    Montant montantADediter = new Montant(99.99);
    double expected = SOMME_DE_BASE - montantADediter.getMontant();
    //when
    compte.debiter(montantADediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test(expected = MetierException.class)
  public void testCompteDebiter_casMontantNull() throws MetierException {
    compte.debiter(null);
  }

  @Test(expected = MontantException.class)
  public void testCompteDebiter_casMontantNegatif() throws MetierException {
    compte.debiter(new Montant(-1));
  }

  @Test
  public void testCompteDebiter_casEntierEnDollard() throws MetierException {
    //give
    Montant montantADediter = new Montant(100, DOLLARD);
    double expected = SOMME_DE_BASE - (montantADediter.getMontant() * DOLLARD.getValeurEnEuro());
    //when
    compte.debiter(montantADediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test
  public void testCompteDebiter_casDoubleEnDollard() throws MetierException {
    //give
    Montant montantADediter = new Montant(99.99, DOLLARD);
    double expected = SOMME_DE_BASE - (montantADediter.getMontant() * DOLLARD.getValeurEnEuro());
    //when
    compte.debiter(montantADediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test(expected = MontantException.class)
  public void testCompteDebiter_casMontantSuperieurSolde() throws MetierException {
    Montant montantADediter = new Montant(SOMME_DE_BASE + 1);
    compte.debiter(montantADediter);
  }
}

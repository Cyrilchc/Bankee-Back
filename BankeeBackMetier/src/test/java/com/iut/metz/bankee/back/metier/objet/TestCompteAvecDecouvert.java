package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.DOLLARD;
import static org.junit.Assert.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class TestCompteAvecDecouvert {
  private CompteAvecDecouvert compte;
  private static final double SOMME_DE_BASE = 200;

  @Before
  public void init() {
    compte = (CompteAvecDecouvert)
            new CompteBuilder()
                    .addNumeroCompte("test")
                    .addDecouvert(200)
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

  @Test
  public void testCompteDebiter_casMontantSuperieurSolde() throws MetierException {
    //give
    double expected = -1;
    Montant montantADediter = new Montant(SOMME_DE_BASE + 1);
    //when
    compte.debiter(montantADediter);
    //then
    assertEquals(compte.getSolde(), expected, 0.1);
  }

  @Test(expected = MontantException.class)
  public void testCompteDebiter_casMontantSuperieurDecouver() throws MetierException {
    //give
    Montant montantADediter = new Montant(SOMME_DE_BASE + compte.getDecouvertAutorise() + 1);
    //when
    compte.debiter(montantADediter);
    //then
    fail();
  }
}

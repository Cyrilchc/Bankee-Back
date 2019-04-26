package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.DOLLARD;
import static org.junit.Assert.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

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
  public void testCompteDebiter_casEntier() {
    //give
    try {
      Montant montantADediter = new Montant(100);
      double expected = SOMME_DE_BASE - montantADediter.getMontant();
      //when
      compte.debiter(montantADediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }

  @Test
  public void testCompteDebiter_casDouble() {
    //give
    try {
      Montant montantADediter = new Montant(99.99);
      double expected = SOMME_DE_BASE - montantADediter.getMontant();
      //when
      compte.debiter(montantADediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }

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
  public void testCompteDebiter_casEntierEnDollard() {
    //give
    try {
      Montant montantADediter = new Montant(100, DOLLARD);
      double expected = SOMME_DE_BASE - (montantADediter.getMontant() * DOLLARD.getValeurEnEuro());
      //when
      compte.debiter(montantADediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }

  @Test
  public void testCompteDebiter_casDoubleEnDollard() {
    //give
    try {
      Montant montantADediter = new Montant(99.99, DOLLARD);
      double expected = SOMME_DE_BASE - (montantADediter.getMontant() * DOLLARD.getValeurEnEuro());
      //when
      compte.debiter(montantADediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }

  @Test
  public void testCompteDebiter_casMontantSuperieurSolde() {
    //give
    Exception expected = new MontantException(MontantExceptionUtils.SOLDE_NEGATIF);
    try {
      Montant montantADediter = new Montant(SOMME_DE_BASE + 1);
      //when
      compte.debiter(montantADediter);
      //then
      fail();
    } catch (Exception e) {
      assertEquals(expected.getMessage(), e.getMessage());
    }
  }
}

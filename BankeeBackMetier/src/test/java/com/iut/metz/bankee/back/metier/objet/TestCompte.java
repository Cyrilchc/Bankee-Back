package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.currency.utils.CurrencyUtils.DOLLARD;
import static org.junit.Assert.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.objet.exception.utils.MontantExceptionUtils;

public class TestCompte {
  private Compte compte;
  private static final double SOMME_DE_BASE = 200;

  @Before
  public void init() {
    compte = new Compte(SOMME_DE_BASE, "test") {
      @Override
      public void doDebiter(Montant montant) {/*not used*/}
    };
  }

  @Test
  public void testCompteCrediter_casEntier() {
    //give
    try {
      Montant montantACrediter = new Montant(100);
      double expected = SOMME_DE_BASE + montantACrediter.getMontant();
      //when
      compte.crediter(montantACrediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }

  @Test
  public void testCompteCrediter_casDouble() {
    //give
    try {
      Montant montantACrediter = new Montant(99.99);
      double expected = SOMME_DE_BASE + montantACrediter.getMontant();
      //when
      compte.crediter(montantACrediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }

  }

  @Test
  public void testCompteCrediter_casMontantNull() {
    //give
    Exception expected = new MontantException(MontantExceptionUtils.MONTANT_NULL);
    //when
    try {
      compte.crediter(null);
      fail();
    } catch (Exception e) {
      //then
      assertTrue(e instanceof  MontantException);
      assertEquals(expected.getMessage(), e.getMessage());
    }
  }

  @Test
  public void testCompteCrediter_casMontantNegatif() {
    //give
    Exception expected = new MontantException(MontantExceptionUtils.MONTANT_NEGATIF);
    //when
    try {
      compte.crediter(new Montant(-1));
      fail();
    } catch (Exception e) {
      //then
      assertTrue(e instanceof  MontantException);
      assertEquals(expected.getMessage(), e.getMessage());
    }
  }

  @Test
  public void testCompteCrediter_casEntierEnDollard() {
    //give
    try {
      Montant montantACrediter = new Montant(100, DOLLARD);
      double expected = SOMME_DE_BASE + (montantACrediter.getMontant()*DOLLARD.getValeurEnEuro());
      //when
      compte.crediter(montantACrediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }

  @Test
  public void testCompteCrediter_casDoubleEnDollard() {
    //give
    try {
      Montant montantACrediter = new Montant(99.99, DOLLARD);
      double expected = SOMME_DE_BASE + (montantACrediter.getMontant()*DOLLARD.getValeurEnEuro());
      //when
      compte.crediter(montantACrediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }

  }
}

package metier;

import static metier.currency.utils.CurrencyUtils.DOLLARD;
import static metier.exception.utils.MontantExceptionUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

import metier.currency.Montant;
import metier.exception.MontantException;

public class TestCompte {
  private Compte compte;
  private static final double SOMME_DE_BASE = 200;

  @Before
  public void init() {
    compte = new Compte(SOMME_DE_BASE, "test") {
      @Override
      public boolean doDebiter(Montant montant) {
        return true;
      }
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
    Exception expected = new MontantException(MONTANT_NULL);
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
    Exception expected = new MontantException(MONTANT_NEGATIF);
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

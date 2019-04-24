package metier;

import static metier.exception.utils.MontantExceptionUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

import metier.curency.Montant;
import metier.exception.MontantException;

public class TestCompte {
  private Compte compte;
  private final double sommeDeBase = 200;

  @Before
  public void init() {
    compte = new Compte(sommeDeBase, "test") {
      @Override
      public boolean debiter(Montant montant) {
        return true;
      }
    };
  }

  @Test
  public void testCompteCrediter_casEntier() {
    //give
    double expected = 300;
    Montant montantACrediter = new Montant(100);
    //when
    compte.crediter(montantACrediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test
  public void testCompteCrediter_casDouble() {
    //give
    double expected = 299.99;
    Montant montantACrediter = new Montant(99.99);
    //when
    compte.crediter(montantACrediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
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
      assertEquals(expected, e);
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
      assertEquals(expected, e);
    }
  }
}

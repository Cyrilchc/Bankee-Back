package metier;

import org.junit.*;
import static org.junit.Assert.*;

import metier.curency.Montant;

public class TestCompte {
  private Compte compte;
  private final double sommeDeBase = 200;

  @Before
  public void init(){
    compte = new Compte(sommeDeBase, "test") {
      @Override
      public boolean debiter(Montant montant) {
        return true;
      }
    };
  }

  @Test
  public void testCompteCrediter_casEntier(){
    //give
    double expected = 300;
    Montant montantACrediter = new Montant(100);
    //when
    compte.crediter(montantACrediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test
  public void testCompteCrediter_casDouble(){
    //give
    double expected = 299.99;
    Montant montantACrediter = new Montant(99.99);
    //when
    compte.crediter(montantACrediter);
    //then
    assertEquals(expected, compte.getSolde(), 0.1);
  }

  @Test
  public void testCompteCrediter_casMontantNull(){
    //give
    Exception expected = new Exception();
    //when
    try {
      compte.crediter(null);
      fail();
    } catch (Exception e) {
      //then
      assertTrue(e instanceof Exception);
    }
  }


}

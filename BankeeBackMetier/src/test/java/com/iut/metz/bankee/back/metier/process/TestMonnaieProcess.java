package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;

/**
 * class de test sur la validation
 * <ul>
 *   <li>monnaie non null</li>
 *   <li>cours du change strictement positif</li>
 *   <li>a un nom</li>
 *   <li>a un symbole</li>
 * </ul>
 */
public class TestMonnaieProcess {
  @Test(expected = MetierException.class)
  public void TestMonnaieProcess_casMontantNull() throws MetierException {
    new MonnaieProcess().isValid(null);
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casMontantNegatif() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(-1, "test", "test"));
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casTauxChangeEgal0() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(0, "test", "test"));
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casMontantNomVide() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(1, "", "test"));
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casMontantSymboleVide() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(1, "test", ""));
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casMontantNomNull() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(1, null, "test"));
  }

  @Test(expected = MonnaieException.class)
  public void TestMonnaieProcess_casMontantSymboleNull() throws MetierException {
    new MonnaieProcess().isValid(new Monnaie(1, "test", null));
  }

  @Test
  public void TestMonnaieProcess_casTauxChangePositif() throws MetierException {
    Assert.assertTrue(new MonnaieProcess().isValid(MONNAIE_PAR_DEFAUT));
  }

  @Test
  public void TestMonnaieProcess_casTauxChangePosif2() throws MetierException {
    Assert.assertTrue(new MonnaieProcess().isValid(DOLLARD));
  }
}

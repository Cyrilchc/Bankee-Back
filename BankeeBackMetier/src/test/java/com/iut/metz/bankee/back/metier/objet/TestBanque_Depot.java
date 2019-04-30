package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.DOLLARD;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.objet.utils.BiConsumerEx;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompteManager.class)
public class TestBanque_Depot {
  private BiConsumerEx<String, Montant> opperation = (num, montant) -> new Banque().depot("test", montant);

  @Test
  public void testDepot_casMontantEuroPositif() throws MetierException {
    new TestBanque_Operation(2,0).casMontantEuroPositif(opperation);
  }

  @Test
  public void testDepot_casMontantDollardPositif() throws MetierException {
    new TestBanque_Operation(2 * DOLLARD.getValeurEnEuro(),0).casMontantDollardPositif(opperation);
  }

  @Test(expected = MetierException.class)
  public void testDepot_casCompteInexistant() throws MetierException {
    new TestBanque_Operation().casCompteInexistant(opperation);
  }

  @Test(expected = ClientException.class)
  public void testDepot_casCompteNull() throws MetierException {
    new TestBanque_Operation().casCompteNull(opperation);
  }

  @Test(expected = MetierException.class)
  public void testDepot_casMontantNull() throws MetierException {
    new TestBanque_Operation().casMontantNull(opperation);
  }

  @Test(expected = MontantException.class)
  public void testDepot_casMontantNegatif() throws MetierException {
    new TestBanque_Operation().casMontantNegatif(opperation);
  }

  @Test(expected = MontantException.class)
  public void testDepot_casMontantVaut0() throws MetierException {
    new TestBanque_Operation().casMontantVaut0(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieVaut0() throws MetierException {
    new TestBanque_Operation().casMonnaieVaut0(opperation);
  }

  @Test(expected = MetierException.class)
  public void testDepot_casMonnaieNull() throws MetierException {
    new TestBanque_Operation().casMonnaieNull(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieNegatif() throws MetierException {
    new TestBanque_Operation().casMonnaieNegatif(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieNomVide() throws MetierException {
    new TestBanque_Operation().casMonnaieNomVide(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieNomNull() throws MetierException {
    new TestBanque_Operation().casMonnaieNomNull(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieSymboleVide() throws MetierException {
    new TestBanque_Operation().casMonnaieSymboleVide(opperation);
  }

  @Test(expected = MonnaieException.class)
  public void testDepot_casMonnaieSymboleNull() throws MetierException {
    new TestBanque_Operation().casMonnaieSymboleNull(opperation);
  }
}

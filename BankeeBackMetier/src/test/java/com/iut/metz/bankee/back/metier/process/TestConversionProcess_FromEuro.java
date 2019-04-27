package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class TestConversionProcess_FromEuro {
  private static final double MONTANT_DE_BASE = 20;
  private Monnaie monnaie;

  @Before
  public void init() {
    monnaie = new Monnaie(1,"z", "z");
  }

  @Test(expected = MetierException.class)
  public void testFromEuro_casMonnaieNull() throws MetierException {
    new ConversionProcess().fromEuro(1, null);
  }

  @Test(expected = MetierException.class)
  public void testFromEuro_casMonnaieNomVide() throws MetierException {
    monnaie = new Monnaie(1, "", "e");
    new ConversionProcess().fromEuro(1, monnaie);
  }

  @Test(expected = MetierException.class)
  public void testFromEuro_casMonnaieNomNull() throws MetierException {
    monnaie = new Monnaie(1, null, "e");
    new ConversionProcess().fromEuro(1, monnaie);
  }

  @Test(expected = MetierException.class)
  public void testFromEuro_casMonnaieSymboleVide() throws MetierException {
    monnaie = new Monnaie(1, "e", "");
    new ConversionProcess().fromEuro(1, monnaie);
  }

  @Test(expected = MetierException.class)
  public void testFromEuro_casMonnaieSymboleNull() throws MetierException {
    monnaie = new Monnaie(1, "e", null);
    new ConversionProcess().fromEuro(1, monnaie);
  }

  @Test(expected = MontantException.class)
  public void testFromEuro_casSommeNegatif() throws MetierException {
    new ConversionProcess().fromEuro(-1, monnaie);
  }

  @Test(expected = MontantException.class)
  public void testFromEuro_casMontant0() throws MetierException {
    new ConversionProcess().fromEuro(0, monnaie);
  }

  @Test
  public void testFromEuro_casEuroVersEuro() throws MetierException {
    //then
    assertEquals(new ConversionProcess().fromEuro(MONTANT_DE_BASE, MONNAIE_PAR_DEFAUT).getMontant()
            , MONTANT_DE_BASE, 0);
  }

  @Test
  public void testFromEuro_casEuroVersDollard() throws MetierException {
    //give
    double expected = MONTANT_DE_BASE / DOLLARD.getValeurEnEuro();
    //when
    double res = new ConversionProcess().fromEuro(MONTANT_DE_BASE, DOLLARD).getMontant();
    //then
    assertEquals(expected, res, 0);
  }

  @Test(expected = MonnaieException.class)
  public void testFromEuro_casEuroVersMonaieTauxNegatif() throws MetierException {
    //give
    monnaie = new Monnaie(-1, "d", "d");
    new ConversionProcess().fromEuro(1, monnaie);
  }

  @Test(expected = MonnaieException.class)
  public void testFromEuro_casEuroVersMonaieTaux0() throws MetierException {
    //give
    monnaie = new Monnaie(-1, "d", "d");
    new ConversionProcess().fromEuro(1, monnaie);
  }
}

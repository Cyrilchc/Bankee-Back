package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;

public class TestBanque_ConversionToEuro {
  private static final double MONTANT_DE_BASE = 20;
  private Montant montant;

  @Before
  public void init() {
    montant = new Montant(MONTANT_DE_BASE);
  }

  @Test(expected = MetierException.class)
  public void testToEuro_casMontantNull() throws MetierException {
    new Banque().conversionToEuro(null);
  }

  @Test(expected = MetierException.class)
  public void testToEuro_casMonnaieNull() throws MetierException {
    new Banque().conversionToEuro(new Montant(MONTANT_DE_BASE, null));
  }

  @Test(expected = MontantException.class)
  public void testToEuro_casMontantNegatif() throws MetierException {
    new Banque().conversionToEuro(new Montant(-1));
  }

  @Test(expected = MontantException.class)
  public void testToEuro_casMontant0() throws MetierException {
    new Banque().conversionToEuro(new Montant(0));
  }

  @Test
  public void testToEuro_casEuroVersEuro() throws MetierException {
    //give
    montant.setMonnaie(MONNAIE_PAR_DEFAUT);
    //then
    assertEquals(new Banque().conversionToEuro(montant).getMontant(), MONTANT_DE_BASE, 0);
  }

  @Test
  public void testToEuro_casEuroVersDollard() throws MetierException {
    //give
    montant.setMonnaie(DOLLARD);
    double expected = MONTANT_DE_BASE * DOLLARD.getValeurEnEuro();
    //when
    double res = new Banque().conversionToEuro(montant).getMontant();
    //then
    assertEquals(expected, res, 0);
  }

  @Test(expected = MonnaieException.class)
  public void testToEuro_casEuroVersMonaieTauxNegatif() throws MetierException {
    //give
    montant.setMonnaie(new Monnaie(-1, "d", "d"));
    new Banque().conversionToEuro(montant);
  }

  @Test(expected = MonnaieException.class)
  public void testToEuro_casEuroVersMonaieTaux0() throws MetierException {
    //give
    montant.setMonnaie(new Monnaie(-1, "d", "d"));
    new Banque().conversionToEuro(montant);
  }
}

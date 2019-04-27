package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;

/**
 * class de test sur la validation
 * <ul>
 *   <li>montant non null</li>
 *   <li>montant strictement positif</li>
 * </ul>
 */
public class TestMontantProcess {
  @Test(expected = MetierException.class)
  public void testMontantProcess_casMontantNull() throws MetierException {
    new MontantProcess().isValid(null);
  }

  @Test(expected = MontantException.class)
  public void testMontantProcess_casMontantNegatif() throws MetierException {
    new MontantProcess().isValid(new Montant(-1, MONNAIE_PAR_DEFAUT));
  }

  @Test(expected = MontantException.class)
  public void testMontantProcess_casMontantEgal0() throws MetierException {
    new MontantProcess().isValid(new Montant(0, MONNAIE_PAR_DEFAUT));
  }

  @Test
  public void testMontantProcess_casMontantPositif() throws MetierException {
    Assert.assertTrue(new MontantProcess().isValid(new Montant(1, MONNAIE_PAR_DEFAUT)));
  }

  @Test
  public void testMontantProcess_casMontantPositifEtEnDollard() throws MetierException {
    Assert.assertTrue(new MontantProcess().isValid(new Montant(1, DOLLARD)));
  }

  @Test(expected = MontantException.class)
  public void testMontantProcess_casMontantNegatifEtEnDollard() throws MetierException {
    new MontantProcess().isValid(new Montant(-1, DOLLARD));
  }

  @Test(expected = MontantException.class)
  public void testMontantProcess_casMontantEgal0EtEnDollard() throws MetierException {
    new MontantProcess().isValid(new Montant(0, DOLLARD));
  }
}

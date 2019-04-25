package metier.currency.utils;

import metier.currency.Monnaie;

public class CurrencyUtils {
  private CurrencyUtils(){}

  public static final Monnaie MONNAIE_PAR_DEFAUT = new Monnaie(1,"euro","€");
  public static final Monnaie DOLLARD = new Monnaie(0.9,"dollard","$");
}

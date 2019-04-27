package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.DOLLARD;
import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;
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

  @Test(expected = MetierException.class)
  public void testCompteCrediter_casMontantNull() throws MetierException {
    compte.crediter(null);
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
      assertTrue(e instanceof MontantException);
      assertEquals(expected.getMessage(), e.getMessage());
    }
  }

  @Test
  public void testCompteCrediter_casEntierEnDollard() {
    //give
    try {
      Montant montantACrediter = new Montant(100, DOLLARD);
      double expected = SOMME_DE_BASE + (montantACrediter.getMontant() * DOLLARD.getValeurEnEuro());
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
      double expected = SOMME_DE_BASE + (montantACrediter.getMontant() * DOLLARD.getValeurEnEuro());
      //when
      compte.crediter(montantACrediter);
      //then
      assertEquals(expected, compte.getSolde(), 0.1);
    } catch (Exception ignore) {
      fail();
    }
  }


  //equals
  @Test
  public void testEquals_deuxCompteSansDecouvert() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(1).build();
    assertTrue(compte1.equals(compte2));
  }

  @Test
  public void testEquals_deuxCompteSansDecouvertPasLeMemeSoldeMaisMemeId() {
    Compte compte1 = new CompteBuilder().addId(1).addSolde(1).build();
    Compte compte2 = new CompteBuilder().addId(1).addSolde(2).build();
    assertTrue(compte1.equals(compte2));
  }

  @Test
  public void testEquals_deuxCompteSansDecouvertPasLeMemeId() {
    Compte compte1 = new CompteBuilder().addId(1).addSolde(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    assertFalse(compte1.equals(compte2));
  }

  @Test
  public void testEquals_deuxCompteAvecDecouvert() {
    Compte compte1 = new CompteBuilder().addId(1).addDecouvert(1).build();
    Compte compte2 = new CompteBuilder().addId(1).addDecouvert(1).build();
    assertTrue(compte1.equals(compte2));
  }

  @Test
  public void testEquals_deuxCompteAvecDecouvertPasLeMemeSoldeMaisMemeId() {
    Compte compte1 = new CompteBuilder().addId(1).addDecouvert(1).addSolde(1).build();
    Compte compte2 = new CompteBuilder().addId(1).addDecouvert(1).addSolde(2).build();
    assertTrue(compte1.equals(compte2));
  }

  @Test
  public void testEquals_deuxCompteAvecDecouvertPasLeMemeId() {
    Compte compte1 = new CompteBuilder().addId(1).addDecouvert(1).build();
    Compte compte2 = new CompteBuilder().addId(2).addDecouvert(1).build();
    assertFalse(compte1.equals(compte2));
  }

  @Test
  public void testEquals_CompteAvecDecouvertEtCompteSansDecouvertMemeId() {
    Compte compte1 = new CompteBuilder().addId(1).addDecouvert(1).build();
    Compte compte2 = new CompteBuilder().addId(1).build();
    assertFalse(compte1.equals(compte2));
  }

  //hash
  @Test
  public void testHash_CompteAvecDecouvert() {
    int id = 1;
    int decouvert = 1;
    int solde = 0;
    String num = "azerty";

    Compte compte = new CompteBuilder().addId(id)
            .addSolde(solde)
            .addNumeroCompte(num)
            .addDecouvert(decouvert).build();
    int hash = compte.hashCode();
    assertEquals(compte.hashCode(), hash);
  }

  @Test
  public void testHash_CompteSansDecouvert() {
    int id = 1;
    int solde = 0;
    String num = "azerty";

    Compte compte = new CompteBuilder().addId(id)
            .addSolde(solde)
            .addNumeroCompte(num).build();
    int hash = Objects.hash(id, solde, num);
    assertEquals(compte.hashCode(), hash);
  }
}

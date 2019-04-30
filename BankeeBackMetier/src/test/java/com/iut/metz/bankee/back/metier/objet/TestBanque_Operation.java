package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.utils.MockUtils.mockCompteDao;
import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;
import static org.junit.Assert.*;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.MetierException;
import com.iut.metz.bankee.back.metier.objet.utils.BiConsumerEx;

public class TestBanque_Operation {

  public double expected;
  public double solde;

  public TestBanque_Operation() {
  }

  public TestBanque_Operation(double expected, double solde) {
    this.expected = expected;
    this.solde = solde;
  }

  public void casMontantEuroPositif(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double expected = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(expected, MONNAIE_PAR_DEFAUT);
    //when
    opperation.accept("test", montant);
    //then
    assertEquals(this.expected, compte.getSolde(), 0);
  }

  public void casMontantDollardPositif(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double enDollard = 2;
    double expected = enDollard * DOLLARD.getValeurEnEuro();
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(enDollard, DOLLARD);
    //when
    opperation.accept("test", montant);
    //then
    assertEquals(this.expected, compte.getSolde(), 0);
  }

  public void casCompteInexistant(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double enDollard = 2;
    Compte compte = new CompteBuilder().addId(-1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(enDollard, DOLLARD);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casCompteNull(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double enDollard = 2;
    mockCompteDao(null);
    Montant montant = new Montant(enDollard, DOLLARD);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMontantNull(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    //when
    opperation.accept("test", null);
    //then
    fail();
  }

  public void casMontantNegatif(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = -2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(argent, MONNAIE_PAR_DEFAUT);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMontantVaut0(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 0;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(argent, MONNAIE_PAR_DEFAUT);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieVaut0(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(0, "d", "d");
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieNull(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Montant montant = new Montant(argent, null);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieNegatif(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(-1, "d", "d");
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieNomVide(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(-1, "", "d");
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieNomNull(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(-1, null, "d");
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieSymboleVide(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(-1, "d", "");
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }

  public void casMonnaieSymboleNull(BiConsumerEx<String, Montant> opperation) throws MetierException {
    //give
    double argent = 2;
    Compte compte = new CompteBuilder().addId(1).addSolde(solde).build();
    mockCompteDao(compte);
    Monnaie monnaie = new Monnaie(-1, "d", null);
    Montant montant = new Montant(argent, monnaie);
    //when
    opperation.accept("test", montant);
    //then
    fail();
  }
}

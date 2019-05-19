package com.iut.metz.bankee.back.metier.objet.builder;

import static org.junit.Assert.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.*;

public class TestCompteBuilder {
  private CompteBuilder builder;
  private Compte compte;

  private int id;
  private double solde;
  private String numeroCompte;
  private double decouvert;

  @Before
  public void init() {
    builder = new CompteBuilder();
    id = -1;
    solde = 0;
    numeroCompte = "";
    decouvert = 0;
  }

  @Test
  public void testCompteBuilder_rienFaire() {
    compte = builder.build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addId() {
    id = 2;
    compte = builder.addId(id).build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addSolde() {
    solde = 2;
    compte = builder.addSolde(solde).build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addNumero() {
    numeroCompte = "azerty";
    compte = builder.addNumeroCompte(numeroCompte).build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addDecouvert() {
    decouvert = 2;
    compte = builder.addDecouvert(decouvert).build();
    testAllCase();
    assertTrue(compte instanceof CompteAvecDecouvert);
    assertEquals(((CompteAvecDecouvert)compte).getDecouvertAutorise(), decouvert, 0);
  }

  @Test
  public void testCompteBuilder_addIdSolde() {
    id = 2;
    solde = 2;
    compte = builder.addId(id)
            .addSolde(solde).build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addIdSoldeNumero() {
    id = 2;
    solde = 2;
    numeroCompte = "azerty";
    compte = builder.addId(id)
            .addSolde(solde)
            .addNumeroCompte(numeroCompte)
            .build();
    testAllCase();
    assertTrue(compte instanceof CompteSansDecouvert);
  }

  @Test
  public void testCompteBuilder_addTout() {
    id = 2;
    solde = 2;
    numeroCompte = "azerty";
    decouvert = 2;
    compte = builder.addId(id)
            .addSolde(solde)
            .addNumeroCompte(numeroCompte)
            .addDecouvert(decouvert)
            .build();
    testAllCase();
    assertTrue(compte instanceof CompteAvecDecouvert);
    assertEquals(((CompteAvecDecouvert)compte).getDecouvertAutorise(), decouvert, 0);
  }

  private void testAllCase() {
    assertEquals(compte.getId(), id);
    assertEquals(compte.getSolde(), solde, 0);
    assertEquals(compte.getNumeroCompte(), numeroCompte);
  }
}

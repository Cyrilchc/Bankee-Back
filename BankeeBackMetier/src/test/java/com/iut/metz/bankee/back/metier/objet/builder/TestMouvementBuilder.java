package com.iut.metz.bankee.back.metier.objet.builder;

import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.Mouvement;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestMouvementBuilder {
  private MouvementBuilder builder;
  private Mouvement mouvement;

  private int id;
  private Date dateMouvement;
  private Compte compte;
  private double somme;
  private boolean debit;

  @Before
  public void init() {
    builder = new MouvementBuilder();
    id = -1;
    dateMouvement = Date.from(Instant.now());
    compte = null;
    somme = 0;
    debit = true;
  }

  @Test
  public void testCompteBuilder_rienFaire() {
    mouvement = builder.build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addId() {
    id = 2;
    mouvement = builder.addId(id).build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addSomme() {
    somme = 2;
    mouvement = builder.addSomme(somme).build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addDate() {
    dateMouvement = Date.from(Instant.ofEpochMilli(123456789));
    mouvement = builder.addDateMouvement(dateMouvement).build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addIsDebit() {
    debit = true;
    mouvement = builder.addIsDebit(debit).build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addIsDebitNull() {
    debit = false;
    mouvement = builder.addIsDebit(null).build();
    testAllCase();
  }

  @Test
  public void testCompteBuilder_addCompte() {
    compte = new Compte();
    mouvement = builder.addCompte(compte).build();
    testAllCase();
  }

  private void testAllCase() {
    assertEquals(mouvement.getId(), id);
    assertEquals(mouvement.getCompte(), compte);
    assertEquals(mouvement.getDateMouvement().getTime()/100, dateMouvement.getTime()/100);
    assertEquals(mouvement.getSomme(), somme, 0);
    assertEquals(mouvement.isDebit(), debit);
  }
}

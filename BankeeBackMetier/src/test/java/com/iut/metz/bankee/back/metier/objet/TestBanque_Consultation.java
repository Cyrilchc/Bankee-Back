package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.utils.MockUtils.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompteManager.class)
public class TestBanque_Consultation {

  @Test
  public void testConsultation_retourneUnCompte() {
    //give
    Compte expected = new CompteBuilder().addId(1).build();
    mockCompteDao(expected);
    //when
    Compte res = new Banque().consultation("test");
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testConsultation_retourneNull() {
    //give
    mockCompteDao(null);
    //when
    Compte res = new Banque().consultation("test");
    //then
    assertNull(res);
  }

  @Test
  public void testConsultation_throw() {
    //give
    mockCompteDaoThrow();
    //when
    Compte res = new Banque().consultation("test");
    //then
    assertNull(res);
  }
}

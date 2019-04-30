package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.utils.MockUtils.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.objet.builder.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestBanque_ConsultationClient {

  @Test
  public void testConsultationClient_retourneUnCompte() {
    //give
    Client expected = new ClientBuilder().addId(1).build();
    mockClientDao(expected);
    //when
    Client res = new Banque().consultationClient("test");
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testConsultationClient_retourneNull() {
    //give
    mockClientDao(null);
    //when
    Client res = new Banque().consultationClient("test");
    //then
    assertNull(res);
  }

  @Test
  public void testConsultationClient_throw() {
    //give
    mockClientDaoThrow();
    //when
    Client res = new Banque().consultationClient("test");
    //then
    assertNull(res);
  }
}

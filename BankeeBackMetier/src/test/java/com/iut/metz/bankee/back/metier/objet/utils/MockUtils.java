package com.iut.metz.bankee.back.metier.objet.utils;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.iut.metz.bankee.back.metier.manager.*;
import com.iut.metz.bankee.back.metier.objet.*;

public class MockUtils {
  private MockUtils(){}

  public static void mockCompteDao(Compte compte) {
    CompteManager dao = mock(CompteManager.class);
    when(dao.getCompteByNumCompte(Mockito.anyString())).thenReturn(compte);
    PowerMockito.mockStatic(CompteManager.class);
    PowerMockito.when(CompteManager.getInstance()).thenReturn(dao);
  }

  public static void mockCompteDaoThrow() {
    CompteManager dao = mock(CompteManager.class);
    when(dao.getCompteByNumCompte(Mockito.anyString())).thenThrow(RuntimeException.class);
    PowerMockito.mockStatic(CompteManager.class);
    PowerMockito.when(CompteManager.getInstance()).thenReturn(dao);
  }

  public static void mockClientDao(Client client) {
    ClientManager dao = mock(ClientManager.class);
    when(dao.getByNumClient(Mockito.anyString())).thenReturn(client);
    PowerMockito.mockStatic(ClientManager.class);
    PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
  }

  public static void mockClientDaoThrow() {
    ClientManager dao = mock(ClientManager.class);
    when(dao.getByNumClient(Mockito.anyString())).thenThrow(RuntimeException.class);
    PowerMockito.mockStatic(ClientManager.class);
    PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
  }
}

package com.iut.metz.bankee.back.metier.manager;

public class DAOManager {

  public CompteManager getCompteInstance() {
    return CompteManager.getInstance();
  }

  public ClientManager getClientInstance() {
    return ClientManager.getInstance();
  }

}

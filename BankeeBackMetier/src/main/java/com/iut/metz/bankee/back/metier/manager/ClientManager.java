package com.iut.metz.bankee.back.metier.manager;

import com.iut.metz.bankee.back.metier.objet.*;

public class ClientManager extends HibernateFactory<Client> {
  private static ClientManager instance;

  private ClientManager() {
    super(Client.class);
  }

  public static ClientManager getInstance() {
    if (instance == null) {
      instance = new ClientManager();
    }
    return instance;
  }
}

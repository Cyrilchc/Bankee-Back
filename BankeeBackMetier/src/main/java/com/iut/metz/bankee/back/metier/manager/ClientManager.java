package com.iut.metz.bankee.back.metier.manager;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;
import org.hibernate.query.Query;

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

  public Client getByNumClient(String numClient) {
    AtomicReference<Client> res = new AtomicReference<>(null);
    Session session = getSession();
    Query query = session.createQuery("from Client as client where client.numeroClient = :numClient");
    query.setParameter("numClient", numClient);
    List<Client> list = query.list();
    list.forEach(res::set);
    return res.get();
  }
}

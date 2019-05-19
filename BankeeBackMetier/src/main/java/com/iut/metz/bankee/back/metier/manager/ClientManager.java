package com.iut.metz.bankee.back.metier.manager;

import com.iut.metz.bankee.back.metier.objet.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    Session session = getSession();
    Query query = session.createQuery("select client from Client as client where client.numeroClient = :numClient");
    query.setParameter("numClient", numClient);
    return (Client) query.getSingleResult();
  }

  public Client getByLogin(String user, String psw) {
    Session session = getSession();
    Query query = session.createQuery("select client from Client as client " +
            "where client.numeroClient = :numClient " +
            "and client.password = :psw");
    query.setParameter("numClient", user);
    query.setParameter("psw", psw);
    return (Client) query.getSingleResult();
  }
}

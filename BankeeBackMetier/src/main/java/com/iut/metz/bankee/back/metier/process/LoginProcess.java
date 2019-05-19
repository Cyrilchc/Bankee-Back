package com.iut.metz.bankee.back.metier.process;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.objet.Client;

public class LoginProcess {
    public Client getClientByLogin(String user, String psw) {
        Client client = ClientManager.getInstance().getByLogin(user, psw);
        return client;
    }
}

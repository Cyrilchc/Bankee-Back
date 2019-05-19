package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.process.LoginProcess;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * permet d'ouvrir un compte pour une personne donné;
 * {
 * "numeroClient": "123456",
 * "password": "1234",
 * }
 */
@Path("login")
public class LoginService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Object isCorrectClient(final Client client) {
        String user = client.getNumeroClient();
        String psw = client.getPassword();
        Client res = new LoginProcess().getClientByLogin(user, psw);
        return res;
    }

}

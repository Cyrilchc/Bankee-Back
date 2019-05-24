package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.process.LoginProcess;

import javax.ws.rs.*;
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


    @GET
    @Path("/{user}/{psw}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isCorrectClient(@PathParam("user") String user, @PathParam("psw") String psw) {
        Client res = new LoginProcess().getClientByLogin(user, psw);
        if (res == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(res).build();
    }

    @Deprecated
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connect(Client client) {
        String user = client.getNumeroClient();
        String psw = client.getPassword();
        Client res = new LoginProcess().getClientByLogin(user, psw);
        if (res == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(res).build();
    }

}

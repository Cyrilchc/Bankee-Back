package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.ClientManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("client/{num}")
public class ClientService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClient(@PathParam("num") String num) {
        return new getDataService().getData(num, data -> ClientManager.getInstance().getByNumClient(data));
    }
}

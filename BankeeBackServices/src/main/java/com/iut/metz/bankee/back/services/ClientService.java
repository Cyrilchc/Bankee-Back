package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_CLIENT;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.manager.ClientManager;

@Path("client")
public class ClientService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getClient(@Context HttpHeaders headers) {
    return new getDataService().getData(headers,
            NUMERO_CLIENT,
            num -> ClientManager.getInstance().getByNumClient(num));
  }
}

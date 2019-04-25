package com.iut.metz.bankee.back.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.objet.*;

@Path("client")
public class ClientService {
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getClient() {
    try {
      Client client = new Client("ddd", "2 rue", "Ligo");
      return Response.ok(client).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
  }
}

package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

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
      return getResponse(client);
    } catch (Exception e) {
      return getError(e);
    }
  }
}

package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_CLIENT;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.manager.*;

@Path("comptes")
public class ComptesService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte(@Context HttpHeaders headers) {
    return new getDataService().getData(headers,
            NUMERO_CLIENT,
            num -> CompteManager.getInstance().getComptesByNumClient(num));
  }
}

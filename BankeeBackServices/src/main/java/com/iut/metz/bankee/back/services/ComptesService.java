package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.CompteManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("comptes/{num}")
public class ComptesService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte(@PathParam("num") String num) {
    return new getDataService().getData(num, data -> CompteManager.getInstance().getComptesByNumClient(data));
  }
}

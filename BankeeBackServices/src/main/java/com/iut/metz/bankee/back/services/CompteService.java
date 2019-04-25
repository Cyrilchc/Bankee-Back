package com.iut.metz.bankee.back.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.objet.*;

@Path("compte")
public class CompteService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte() {
    try {
      Compte compte = new CompteSansDecouvert(-1, 200, "test");
      return Response.ok(compte).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
  }
}

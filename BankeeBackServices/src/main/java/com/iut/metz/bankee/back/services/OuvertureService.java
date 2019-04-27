package com.iut.metz.bankee.back.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.services.metier.Personne;

/**
 * permet d'ouvrir un compte pour une personne donné;
 * {
 *   nom: String,
 *   adresse: String
 * }
 */
@Path("ouverture")
public class OuvertureService {

  @POST
  @Consumes({ MediaType.APPLICATION_JSON })
  @Produces({ MediaType.APPLICATION_JSON })
  public Response ouvrirComptre(@Context HttpHeaders headers, final Personne personne) {
    return Response.ok().build();
  }

}

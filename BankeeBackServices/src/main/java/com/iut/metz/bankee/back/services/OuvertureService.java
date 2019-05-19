package com.iut.metz.bankee.back.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.objet.Banque;
import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.objet.Personne;

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
  public Response creationClient(@Context HttpHeaders headers, final Personne personne) {
    try {
      Client client = new Banque().creationClient(personne);
      return Response.ok(client).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

}

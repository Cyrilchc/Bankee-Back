package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.objet.Banque;
import com.iut.metz.bankee.back.metier.objet.Virement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * permet de faire des virement par appel REST
 * document :
 * {
 *   receveur:String,
 *   donneur:String,
 *   somme:double,
 *   nomMonnaie:String
 * }
 */
@Path("virement")
public class VirementService {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response produceVirement(final Virement virement) {
    try {
      new Banque().doVirement(virement);
      return Response.ok().entity(virement).build();
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity(e)
              .build();
    }
  }


}

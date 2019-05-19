package com.iut.metz.bankee.back.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.services.metier.Virement;

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
  @Consumes({ MediaType.APPLICATION_JSON })
  @Produces({ MediaType.APPLICATION_JSON })
  public Response produceVirement(final Virement virement) {
    return Response.ok().entity(virement).build();
  }
}

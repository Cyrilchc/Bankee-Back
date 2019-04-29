package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.CompteManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * service ne s'occupant que d'un compte
 */

@Path("compte/{num}")
public class CompteService {

  /**
   * retourne le compte demandé en fonction de son numéro de compte
   */
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getClient(@PathParam("num") String num) {
    return new getDataService().getData(num, data -> CompteManager.getInstance().getCompteByNumCompte(data));
  }
}

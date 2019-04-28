package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.CompteManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_COMPTE;

/**
 * service ne s'occupant que d'un compte
 */

@Path("compte")
public class CompteService {

  /**
   * retourne le compte demandé en fonction de son numéro de compte
   */

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getClient(@Context HttpHeaders headers) {
    return new getDataService().getData(headers,
            NUMERO_COMPTE,
            num -> CompteManager.getInstance().getCompteByNumCompte(num));
  }
}

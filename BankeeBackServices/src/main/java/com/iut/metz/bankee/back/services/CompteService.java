package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_COMPTE;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.manager.CompteManager;

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

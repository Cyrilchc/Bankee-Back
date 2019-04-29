package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.CompteManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_COMPTE;
import static com.iut.metz.bankee.back.services.util.ServiceConstante.NUMERO_NON_VALIDE;
import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

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
    try {
      if (num == null || num.equals("")) {
        return badRequest(NUMERO_NON_VALIDE);
      }
      Object object = CompteManager.getInstance().getCompteByNumCompte(num);
      if (object == null) {
        return badRequest(NUMERO_NON_VALIDE);
      }
      return getResponse(object);
    } catch (Exception e) {
      return getError(e);
    }
  }
}

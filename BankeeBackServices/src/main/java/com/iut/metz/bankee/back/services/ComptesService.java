package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_CLIENT;
import static com.iut.metz.bankee.back.services.util.ServiceConstante.NUMERO_NON_VALIDE;
import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.manager.*;

@Path("comptes/{num}")
public class ComptesService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte(@PathParam("num") String num) {
    try {
      if (num == null || num.equals("")) {
        return badRequest(NUMERO_NON_VALIDE);
      }
      Object object = CompteManager.getInstance().getComptesByNumClient(num);
      if (object == null) {
        return badRequest(NUMERO_NON_VALIDE);
      }
      return getResponse(object);
    } catch (Exception e) {
      return getError(e);
    }
  }
}

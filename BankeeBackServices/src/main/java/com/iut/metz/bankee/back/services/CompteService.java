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
  public Response getClient() {
    return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .entity("")
            .build();
  }
}

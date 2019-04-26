package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.log4j.Logger;

import com.iut.metz.bankee.back.metier.objet.*;
import com.iut.metz.bankee.back.metier.process.ObjectProcess;

@Path("compte")
public class CompteService {
  private Logger log = Logger.getLogger(CompteService.class);

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte(@Context HttpHeaders headers) {
    if (isAllows(headers)) {
      try {
        Compte compte = new CompteSansDecouvert(-1, 200, "test");
        return getResponse(compte);
      } catch (Exception e) {
        return getError(e);
      }
    } else {
      return getNotAuthorized();
    }
  }
}

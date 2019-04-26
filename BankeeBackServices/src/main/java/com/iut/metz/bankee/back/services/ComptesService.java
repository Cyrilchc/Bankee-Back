package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.objet.*;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.services.util.ServiceUtils;

@Path("comptes")
public class ComptesService {

  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  public Response getCompte() {
    try {
      Compte compte1 = new CompteBuilder().addNumeroCompte("test1").build();
      Compte compte2 = new CompteBuilder().addNumeroCompte("test2").addSolde(200).build();
      Compte compte3 = new CompteBuilder().addNumeroCompte("test3").addSolde(154.89).addDecouvert(20).build();
      List<Compte> comptes = Arrays.asList(compte1, compte2, compte3);
      return getResponse(comptes);
    } catch (Exception e) {
      return getError(e);
    }
  }
}

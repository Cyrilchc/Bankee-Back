package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.objet.Banque;
import com.iut.metz.bankee.back.metier.objet.Mouvement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("mouvement/{numCompte}")
public class MouvementService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMouvement(@PathParam("numCompte") String numCompte) {
        List<Mouvement> mouvementList = new Banque().getMovementByCompte(numCompte);
        return Response.ok(mouvementList).build();
    }
}

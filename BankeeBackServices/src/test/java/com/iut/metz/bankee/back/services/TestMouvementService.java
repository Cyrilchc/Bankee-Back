package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.MouvementManager;
import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.Mouvement;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.builder.MouvementBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MouvementManager.class)
public class TestMouvementService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(MouvementService.class);
    }

    private void mockDao(List<Mouvement> mouvements) {
        MouvementManager dao = mock(MouvementManager.class);
        when(dao.getByCompte(Mockito.any())).thenReturn(mouvements);
        PowerMockito.mockStatic(MouvementManager.class);
        PowerMockito.when(MouvementManager.getInstance()).thenReturn(dao);
    }

    private Response getResponse(String numCompte){
        Invocation.Builder builder = target("/mouvement/" + numCompte).request();
        return (Response) builder.get();
    }

    @Test
    public void testMouvementService_RetourneUnMouvement(){
        List<Mouvement> mouvements = createMvtList();
        String expected = "{\"id\":-1,\"solde\":0.0,\"numeroCompte\":\"\"}";
        mockDao(mouvements);
        Response response = getResponse("1");
        String res = response.readEntity(String.class);
        assertEquals(javax.ws.rs.core.Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, res);
    }

    private List<Mouvement> createMvtList(String... libelle) {
        List<Mouvement> mouvements = new ArrayList<>();
        Arrays.stream(libelle).forEach(lib -> {
            Mouvement mouvement = new MouvementBuilder().addLib(lib).build();
            mouvements.add(mouvement);
        });
        return mouvements;
    }

}

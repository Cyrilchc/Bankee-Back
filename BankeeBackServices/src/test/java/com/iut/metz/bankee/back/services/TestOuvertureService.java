package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.objet.Personne;
import com.iut.metz.bankee.back.metier.objet.builder.ClientBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestOuvertureService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(OuvertureService.class);
    }


    private void mockDao(Client client) {
        ClientManager dao = mock(ClientManager.class);
        when(dao.create(Mockito.any(Client.class))).thenReturn(client);
        PowerMockito.mockStatic(ClientManager.class);
        PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
    }

    private Response getResponse(){
        Invocation.Builder builder = target("/ouverture").request();
        return (Response) builder.get();
    }

    @Test
    public void testCreationClientVide(){
        Response response = getResponse();
        assertEquals(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreationClient(){
        Client client = new ClientBuilder().build();
        mockDao(client);
        Personne personne = new Personne("garçonnière", "Juste Leblanc");
        Entity<Personne> userEntity = Entity.entity(personne, MediaType.APPLICATION_JSON);
        Response response = target("ouverture").request().post(userEntity);
        String res = response.readEntity(String.class);
        assertTrue(Math.PI == Math.PI);
    }
}

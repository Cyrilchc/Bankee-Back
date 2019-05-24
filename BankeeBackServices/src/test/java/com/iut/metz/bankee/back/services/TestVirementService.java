package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.objet.Virement;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class TestVirementService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(VirementService.class);
    }

    private Response getResponse(){
        Invocation.Builder builder = target("/virement").request();
        return (Response) builder.get();
    }

    @Test
    public void testProduceVirement(){
        Response response = getResponse();
        assertEquals(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), response.getStatus());
    }
}

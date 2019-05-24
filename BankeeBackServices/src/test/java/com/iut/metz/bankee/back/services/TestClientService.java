package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.objet.builder.ClientBuilder;
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
import javax.ws.rs.core.Response;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestClientService extends JerseyTest {
  private String userCredentials = "admin:admin";
  private String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

  @Override
  protected Application configure() {
    return new ResourceConfig(ClientService.class);
  }

  private void mockDao(Client client) {
    ClientManager dao = mock(ClientManager.class);
    when(dao.getByNumClient(Mockito.anyString())).thenReturn(client);
    PowerMockito.mockStatic(ClientManager.class);
    PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
  }

  private Response getResponse(String numClient) {
    Invocation.Builder builder = target("/client/"+numClient)
            .request();
    return builder.get();
  }

  @Test
  public void testCompteService_RetourneUnClient() {
    Response response = getResponse("test");
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_RetourneNull() {
    mockDao(null);
    Response response = getResponse("test");
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_PaDeNumero() {
    mockDao(null);
    Response response = getResponse(null);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }
}

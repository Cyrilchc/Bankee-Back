package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Base64;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.iut.metz.bankee.back.metier.manager.*;
import com.iut.metz.bankee.back.metier.objet.*;
import com.iut.metz.bankee.back.metier.objet.builder.*;

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

  private Response getResponse() {
    return getResponse(null);
  }

  private Response getResponse(String auth) {
    return getResponse(auth, "test");
  }

  private Response getResponse(String auth, String numClient) {
    Invocation.Builder builder = target("/client")
            .request();
    if (auth != null) {
      builder.header("Authorization", auth);
    }
    if (numClient != null) {
      builder.header(NUMERO_CLIENT, numClient);
    }
    return builder.get();
  }

  @Test
  public void testClientService_sansMotDePasse() {
    Response response = getResponse();
    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  public void testClientService_RetourneUnClient() {
    Client client = new ClientBuilder().build();
    mockDao(client);
    Response response = getResponse(basicAuth);
    String res = response.readEntity(String.class);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testClientService_RetourneNull() {
    mockDao(null);
    Response response = getResponse(basicAuth);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testClientService_PaDeNumero() {
    mockDao(null);
    Response response = getResponse(basicAuth, null);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }
}

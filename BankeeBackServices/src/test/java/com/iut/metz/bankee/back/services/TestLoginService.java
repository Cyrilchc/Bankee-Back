package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.builder.ClientBuilder;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestLoginService extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(LoginService.class);
  }

  private void mockDao(Client client) {
    ClientManager dao = mock(ClientManager.class);
    when(dao.getByLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(client);
    PowerMockito.mockStatic(ClientManager.class);
    PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
  }

  private Response getResponse() {
    Invocation.Builder builder = target("/login/1/1")
            .request();
    return builder.get();
  }

  @Test
  public void testisCorrectClient() {
    Client client = new ClientBuilder().build();
    String expected = "{\"id\":-1,\"numeroClient\":\"\",\"adresse\":\"\",\"nom\":\"\",\"password\":\"\",\"comptes\":[]}";
    mockDao(client);
    Response response = getResponse();
    String res = response.readEntity(String.class);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expected, res);
  }

  @Test
  public void testisNotCorrectClient() {
    mockDao(null);
    Response response = getResponse();
    assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
  }

  @Test
  public void testSuccesConnect(){
    Client client = new ClientBuilder().build();
    String expected = "{\"id\":-1,\"numeroClient\":\"\",\"adresse\":\"\",\"nom\":\"\",\"password\":\"\",\"comptes\":[]}";
    mockDao(client);
  }
}

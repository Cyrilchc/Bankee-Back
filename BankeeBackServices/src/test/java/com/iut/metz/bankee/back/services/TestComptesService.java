package com.iut.metz.bankee.back.services;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.manager.CompteManager;
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
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestComptesService extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(ComptesService.class);
  }

  private void mockDao(List<Compte> comptes) {
    ClientManager dao = mock(ClientManager.class);
    when(dao.getByNumClient(Mockito.anyString())).thenReturn(new ClientBuilder().addComptes(comptes).build());
    PowerMockito.mockStatic(ClientManager.class);
    PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
  }

  private Response getResponse(String numClient) {
    Invocation.Builder builder = target("/comptes/"+numClient)
            .request();
    return builder.get();
  }

  @Test
  public void testComptesService_RetourneUnCompte() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    String expected = "{\"id\":-1,\"solde\":0.0,\"numeroCompte\":\"\",\"mouvements\":[]}";
    mockDao(Arrays.asList(compte1, compte2));
    Response response = getResponse("test");
    String res = response.readEntity(String.class);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expected, res);
  }

  @Test
  public void testComptesService_RetourneNull() {
    mockDao(null);
    Response response = getResponse("test");
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testComptesService_PaDeNumero() {
    mockDao(null);
    Response response = getResponse(null);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}

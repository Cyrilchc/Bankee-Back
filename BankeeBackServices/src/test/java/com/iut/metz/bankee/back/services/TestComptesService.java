package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

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

import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompteManager.class)
public class TestComptesService extends JerseyTest {
  private String userCredentials = "admin:admin";
  private String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

  @Override
  protected Application configure() {
    return new ResourceConfig(ComptesService.class);
  }

  private void mockDao(List<Compte> comptes) {
    CompteManager dao = mock(CompteManager.class);
    when(dao.getComptesByNumClient(Mockito.anyString())).thenReturn(comptes);
    PowerMockito.mockStatic(CompteManager.class);
    PowerMockito.when(CompteManager.getInstance()).thenReturn(dao);
  }

  private Response getResponse() {
    return getResponse(null);
  }

  private Response getResponse(String auth) {
    return getResponse(auth, "test");
  }

  private Response getResponse(String auth, String numCompte) {
    Invocation.Builder builder = target("/comptes")
            .request();
    if (auth != null) {
      builder.header("Authorization", auth);
    }
    if (numCompte != null) {
      builder.header(NUMERO_CLIENT, numCompte);
    }
    return builder.get();
  }

  @Test
  public void testComptesService_sansMotDePasse() {
    Response response = getResponse();
    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  public void testComptesService_RetourneUnCompte() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    String expected = "[{\"id\":1,\"solde\":0.0,\"numeroCompte\":\"\"},{\"id\":2,\"solde\":0.0,\"numeroCompte\":\"\"}]";
    mockDao(Arrays.asList(compte1, compte2));
    Response response = getResponse(basicAuth);
    String res = response.readEntity(String.class);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expected, res);
  }

  @Test
  public void testComptesService_RetourneNull() {
    mockDao(null);
    Response response = getResponse(basicAuth);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testComptesService_PaDeNumero() {
    mockDao(null);
    Response response = getResponse(basicAuth, null);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }
}

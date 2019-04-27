package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.CustomHeader.NUMERO_COMPTE;
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

import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompteManager.class)
public class TestCompteService extends JerseyTest {
  private String userCredentials = "admin:admin";
  private String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

  @Override
  protected Application configure() {
    return new ResourceConfig(CompteService.class);
  }

  private void mockDao(Compte compte) {
    CompteManager dao = mock(CompteManager.class);
    when(dao.getCompteByNumCompte(Mockito.anyString())).thenReturn(compte);
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
    Invocation.Builder builder = target("/compte")
            .request();
    if (auth != null) {
      builder.header("Authorization", auth);
    }
    if (numCompte != null) {
      builder.header(NUMERO_COMPTE, numCompte);
    }
    return builder.get();
  }

  @Test
  public void testCompteService_sansMotDePasse() {
    Response response = getResponse();
    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_mauvaisCredential() {
    Response response = getResponse("");
    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_RetourneUnCompte() {
    Compte compte = new CompteBuilder().build();
    String expected = "{\"id\":-1,\"solde\":0.0,\"numeroCompte\":\"\"}";
    mockDao(compte);
    Response response = getResponse(basicAuth);
    String res = response.readEntity(String.class);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(expected, res);
  }

  @Test
  public void testCompteService_RetourneNull() {
    mockDao(null);
    Response response = getResponse(basicAuth);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_PaDeNumero() {
    mockDao(null);
    Response response = getResponse(basicAuth, null);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCompteService_Error() {
    CompteManager dao = mock(CompteManager.class);
    when(dao.getCompteByNumCompte(Mockito.anyString())).thenThrow(RuntimeException.class);
    PowerMockito.mockStatic(CompteManager.class);
    PowerMockito.when(CompteManager.getInstance()).thenReturn(dao);
    Response response = getResponse(basicAuth);
    assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
  }
}

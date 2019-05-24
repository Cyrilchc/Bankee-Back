package com.iut.metz.bankee.back.metier.process;

import com.iut.metz.bankee.back.metier.manager.ClientManager;
import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.metier.objet.Client;
import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.builder.ClientBuilder;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientManager.class)
public class TestLoginProcess {

    private void mockDao(Client client) {
        ClientManager dao = mock(ClientManager.class);
        when(dao.getByLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(client);
        PowerMockito.mockStatic(ClientManager.class);
        PowerMockito.when(ClientManager.getInstance()).thenReturn(dao);
    }

    @Test
    public void testLoginProcess_RetourneCompte() {
        Client client = new ClientBuilder().build();
        mockDao(client);
        Client expected = new LoginProcess().getClientByLogin("", "");
        assertEquals(client, expected);
    }

    @Test(expected = Exception.class)
    public void testLoginProcess_RetourneNull() {
        Client client = new ClientBuilder().build();
        mockDao(null);
        Client expected = new LoginProcess().getClientByLogin("", "");
        assertEquals(client, expected);
    }
}

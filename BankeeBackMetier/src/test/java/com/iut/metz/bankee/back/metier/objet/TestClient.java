package com.iut.metz.bankee.back.metier.objet;

import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.exception.ClientException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestClient {
    
    Client client;

    @Before
    public void setUp() {
        client = new Client("CL1", "4 rue des peupliers", "MR JEAN DUPONT", "1234");
    }

    @Test
    public void testInitialisationListComptes() {
        int expected = 0;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }

    public void testAjoutCompteNull() throws ClientException {
        Compte compteNull = null;
        client.ajouterCompte(compteNull); // Doit throw CompteException
        int expected = 0;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }

    @Test
    public void testAjoutCompteAvecDecouvert() throws ClientException {
        Compte compteAvecDecouvert1 = new CompteBuilder().addId(1).addDecouvert(500).build();
        Compte compteAvecDecouvert2 = new CompteBuilder().addId(2).addDecouvert(300).build();
        client.ajouterCompte(compteAvecDecouvert1);
        client.ajouterCompte(compteAvecDecouvert2);
        int expected = 2;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }

    @Test
    public void testAjoutCompteSansDecouvert() throws ClientException {
        Compte compteSansDecouvert1 = new CompteBuilder().addId(1).build();
        Compte compteSansDecouvert2 = new CompteBuilder().addId(2).build();
        Compte compteSansDecouvert3 = new CompteBuilder().addId(3).build();
        client.ajouterCompte(compteSansDecouvert1,compteSansDecouvert2,compteSansDecouvert3);
        int expected = 3;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }

    @Test
    public void testAjoutCompteAvecEtSansDecouvert() throws ClientException {
        Compte compteAvecDecouvert1 = new CompteBuilder().addId(1).addDecouvert(500).build();
        Compte compteAvecDecouvert2 = new CompteBuilder().addId(2).build();
        Compte compteSansDecouvert1 = new CompteBuilder().addId(3).addDecouvert(300).build();
        Compte compteSansDecouvert2 = new CompteBuilder().addId(4).build();
        client.ajouterCompte(compteAvecDecouvert1,compteAvecDecouvert2,compteSansDecouvert1,compteSansDecouvert2);
        int expected = 4;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }
    
    @Test
    public void testAjoutDeuxComptesIdentiques() {
        Compte compte1 = new CompteBuilder().addId(1).addDecouvert(500).build();
        Compte compte2 = new CompteBuilder().addId(2).build();
        Compte compte3 = new CompteBuilder().addId(3).addDecouvert(300).build();
        Compte compte4 = new CompteBuilder().addId(3).build();
        client.ajouterCompte(compte1,compte2,compte3,compte4);
        int expected = 3;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }
 
}
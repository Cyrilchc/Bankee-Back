//package com.iut.metz.bankee.back.metier.objet;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author Lo�c NOEL
// */
//public class TestClient {
//    
//    Client client;
//
//    @Before
//    public void setUp() {
//        
//        client = new Client("CL1", "4 rue des peupliers", "MR JEAN DUPONT");
//    }
//
//    @Test
//    public void testInitialisationListComptes() {
//        int expected = 0;
//        int out = client.nbComptes();
//        Assert.assertEquals(expected, out);
//    }
//    
//    @Test(expected = ClientException.class)
//    public void testAjoutCompteNull() throws ClientException {
//        Compte compte = null;
//        boolean out = client.ajouterCompte(compte);
//    }
//    
//    @Test
//    public void testAjoutCompteAvecDecouvert() {
//        // TODO
//    }
//    
//    @Test
//    public void testAjoutCompteSansDecouvert() {
//        // TODO
//    }
//    
//    
//}

package metier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Loïc NOEL
 */
public class TestClient {
    
    Client client;

    @Before
    public void setUp() {
        
        client = new Client("CL1", "4 rue des peupliers", "MR JEAN DUPONT");
    }

    @Test
    public void testInitialisationListComptes() {
        int expected = 0;
        int out = client.nbComptes();
        Assert.assertEquals(expected, out);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAjoutCompteNull() {
        Compte compte = null;
        boolean out = client.ajouterCompte(compte);
    }
    
    @Test
    public void testAjoutCompteAvecDecouvert() {
        // TODO
    }
    
    @Test
    public void testAjoutCompteSansDecouvert() {
        // TODO
    }
    
    
}

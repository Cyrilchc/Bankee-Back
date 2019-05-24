package com.iut.metz.bankee.back.metier.objet.builder;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.*;

public class TestClientBuilder {
  private ClientBuilder builder;
  private Client client;

  private int id;
  private String numeroClient;
  private String adresse;
  private String nom;
  private String psw;
  private List<Compte> comptes;

  @Before
  public void init() {
    builder = new ClientBuilder();
    id = -1;
    numeroClient = "";
    adresse = "";
    nom = "";
    psw = "";
    comptes = new ArrayList<>();
  }

  @Test
  public void testClientBuilder_rienFaire() {
    client = builder.build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addId() {
    id = 2;
    client = builder.addId(id).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addNumeroClient() {
    numeroClient = "azerty";
    client = builder.addNumeroClient(numeroClient).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addAdresse() {
    adresse = "azerty";
    client = builder.addAdresse(adresse).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addNom() {
    nom = "azerty";
    client = builder.addNomm(nom).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addPsw() {
    psw = "azerty";
    client = builder.addPassword(psw).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addToutSaufComptes() {
    id = 2;
    numeroClient = "azerty";
    adresse = "azerty";
    nom = "azerty";
    client = builder
            .addId(id)
            .addNomm(nom)
            .addNumeroClient(numeroClient)
            .addAdresse(adresse)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addUnComptes() {
    Compte compte = new CompteBuilder().addId(1).build();
    comptes = Collections.singletonList(compte);
    client = builder.addCompte(compte).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxComptesMisDunCoup() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder.addCompte(compte1, compte2).build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxComptesMisSeparement() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addCompte(compte1)
            .addCompte(compte2)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxComptesEtUnNull() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addCompte(compte1)
            .addCompte(compte2)
            .addCompte((Compte) null)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxComptesEtUnNullDunCoup() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addCompte(compte1, compte2, null)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxMemeComptes() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addCompte(compte1, compte2, compte1)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addListeCompte() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addComptes(comptes)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addListeCompteNull() {
    client = builder
            .addComptes(null)
            .build();
    testAllCase();
  }

  @Test
  public void testClientBuilder_addDeuxMemeComptesList() {
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    List<Compte> test = Arrays.asList(compte1, compte2, compte1);
    comptes = Arrays.asList(compte1, compte2);
    client = builder
            .addComptes(test)
            .build();
    testAllCase();
  }

  private void testAllCase() {
    assertEquals(client.getId(), id);
    assertEquals(client.getNom(), nom);
    assertEquals(client.getPassword(), psw);
    assertEquals(client.getAdresse(), adresse);
    assertEquals(client.getNumeroClient(), numeroClient);
    assertEquals(client.getComptes(), comptes);
  }
}

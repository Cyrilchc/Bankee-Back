package com.iut.metz.bankee.back.metier.objet.builder;

import java.util.*;

import com.iut.metz.bankee.back.metier.objet.*;
import com.iut.metz.bankee.back.metier.process.ListComptesProcess;

public class ClientBuilder {
  private int id;
  private String numeroClient;
  private String adresse;
  private String nom;
  private String psw;
  private List<Compte> comptes;

  public ClientBuilder(){
    id = -1;
    numeroClient = "";
    nom = "";
    adresse = "";
    psw = "";
    comptes = new ArrayList<>();
  }

  public ClientBuilder addId(int id) {
    this.id = id;
    return this;
  }

  public ClientBuilder addNumeroClient(String numeroClient) {
    this.numeroClient = numeroClient;
    return this;
  }

  public ClientBuilder addNomm(String nom) {
    this.nom = nom;
    return this;
  }

  public ClientBuilder addAdresse(String adresse) {
    this.adresse = adresse;
    return this;
  }

  public ClientBuilder addPassword(String psw) {
    this.psw = psw;
    return this;
  }

  public ClientBuilder addCompte(Compte... comptes) {
    this.comptes = new ListComptesProcess().addCompte(this.comptes, comptes);
    return this;
  }

  public ClientBuilder addComptes(List<Compte> comptes) {
    this.comptes = new ListComptesProcess().addCompte(this.comptes, comptes);
    return this;
  }

  public Client build() {
    return new Client(id, numeroClient, adresse, nom, psw, comptes);
  }
}

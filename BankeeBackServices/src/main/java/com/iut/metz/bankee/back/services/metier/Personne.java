package com.iut.metz.bankee.back.services.metier;

public class Personne {
  private String adresse;
  private String nom;

  public Personne(String adresse, String nom) {
    this.adresse = adresse;
    this.nom = nom;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
}

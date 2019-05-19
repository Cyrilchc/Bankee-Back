package com.iut.metz.bankee.back.services.metier;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;

public class Virement {
  private String receveur;
  private String donneur;
  private double somme;
  private String nomMonnaie;

  public Virement() {
  }

  public Virement(String receveur, String donneur, double somme, String nomMonnaie) {
    this.receveur = receveur;
    this.donneur = donneur;
    this.somme = somme;
    this.nomMonnaie = nomMonnaie;
  }

  public String getNomMonnaie() {
    return nomMonnaie;
  }

  public void setNomMonnaie(String nomMonnaie) {
    this.nomMonnaie = nomMonnaie;
  }

  public String getReceveur() {
    return receveur;
  }

  public void setReceveur(String receveur) {
    this.receveur = receveur;
  }

  public String getDonneur() {
    return donneur;
  }

  public void setDonneur(String donneur) {
    this.donneur = donneur;
  }

  public double getSomme() {
    return somme;
  }

  public void setSomme(double somme) {
    this.somme = somme;
  }
}

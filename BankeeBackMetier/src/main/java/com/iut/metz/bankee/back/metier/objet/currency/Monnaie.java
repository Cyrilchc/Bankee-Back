package com.iut.metz.bankee.back.metier.objet.currency;

public class Monnaie {
  private int id;
  private double valeurEnEuro;
  private String nom;
  private String symbole;

  public Monnaie(int id, double valeurEnEuro, String nom, String symbole) {
    this.id = id;
    this.valeurEnEuro = valeurEnEuro;
    this.nom = nom;
    this.symbole = symbole;
  }

  public Monnaie(double valeurEnEuro, String nom, String symbole) {
    this.valeurEnEuro = valeurEnEuro;
    this.nom = nom;
    this.symbole = symbole;
  }

  public double getValeurEnEuro() {
    return valeurEnEuro;
  }

  public void setValeurEnEuro(double valeurEnEuro) {
    this.valeurEnEuro = valeurEnEuro;
  }

  public String getNom() {
    return nom;
  }

  public String getSymbole() {
    return symbole;
  }
}

package com.iut.metz.bankee.back.metier.objet.builder;

import com.iut.metz.bankee.back.metier.objet.*;

public class CompteBuilder {

  private int id;
  private double solde;
  private String numeroCompte;
  private Double decouvert;

  public CompteBuilder(){
    id = -1;
    solde = 0;
    numeroCompte = "";
    decouvert = null;
  }

  public CompteBuilder addId(int id) {
    this.id = id;
    return this;
  }

  public CompteBuilder addSolde(double solde) {
    this.solde = solde;
    return this;
  }

  public CompteBuilder addNumeroCompte(String numeroCompte) {
    this.numeroCompte = numeroCompte;
    return this;
  }

  public CompteBuilder addDecouvert(double decouvert) {
    this.decouvert = decouvert;
    return this;
  }

  public Compte build() {
    Compte compte;
    if (decouvert == null) {
      compte = new CompteSansDecouvert(id, solde, numeroCompte);
    } else {
      compte = new CompteAvecDecouvert(id, solde, numeroCompte, decouvert);
    }
    return compte;
  }
}

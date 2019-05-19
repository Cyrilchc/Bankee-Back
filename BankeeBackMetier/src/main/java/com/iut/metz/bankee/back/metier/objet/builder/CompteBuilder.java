package com.iut.metz.bankee.back.metier.objet.builder;

import com.iut.metz.bankee.back.metier.objet.*;
import com.iut.metz.bankee.back.metier.process.ListComptesProcess;
import com.iut.metz.bankee.back.metier.process.ListMouvementProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompteBuilder {

  private int id;
  private double solde;
  private String numeroCompte;
  private Double decouvert;
  private List<Mouvement> mouvements;

  public CompteBuilder(){
    id = -1;
    solde = 0;
    numeroCompte = "";
    decouvert = null;
    mouvements = new ArrayList<>();
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

  public CompteBuilder addMouvement(Mouvement... mouvements) {
    return addMouvement(Arrays.asList(mouvements));
  }

  public CompteBuilder addMouvement(List<Mouvement> mouvements) {
    this.mouvements = new ListMouvementProcess().addMouvement(this.mouvements, mouvements);
    return this;
  }

  public Compte build() {
    Compte compte;
    if (decouvert == null) {
      compte = new CompteSansDecouvert(id, solde, numeroCompte, mouvements);
    } else {
      compte = new CompteAvecDecouvert(id, solde, numeroCompte, decouvert, mouvements);
    }
    return compte;
  }
}

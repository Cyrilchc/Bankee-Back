package com.iut.metz.bankee.back.metier.objet.builder;

import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.Mouvement;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

public class MouvementBuilder {
    private int id;
    private Date dateMouvement;
    private Compte compte;
    private double somme;
    private String lib;
    private boolean debit;

    public MouvementBuilder() {
        id = -1;
        dateMouvement = Date.from(Instant.now());
        compte = null;
        somme = 0;
        lib = "";
        debit = true;
    }

    public MouvementBuilder addId(int id) {
        this.id = id;
        return this;
    }

    public MouvementBuilder addDateMouvement(Date date) {
        this.dateMouvement = date;
        return this;
    }

    public MouvementBuilder addCompte(Compte compte) {
        this.compte = compte;
        return this;
    }

    public MouvementBuilder addSomme(double somme) {
        this.somme = somme;
        return this;
    }

    public MouvementBuilder addIsDebit(Boolean debit) {
        this.debit = Boolean.TRUE.equals(debit);
        return this;
    }

    public MouvementBuilder addLib(String lib) {
        this.lib = lib;
        return this;
    }

    public Mouvement build() {
        return new Mouvement(id, dateMouvement, compte, somme, lib, debit);
    }
}

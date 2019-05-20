package com.iut.metz.bankee.back.metier.objet;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mouvement")
public class Mouvement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_mouvement")
    private Date dateMouvement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id")
    private Compte compte;
    @Column(name = "somme")
    private double somme;
    @Column(name = "debit")
    private boolean debit;
    @Column(name = "lib")
    private String lib;

    public Mouvement() {}

    public Mouvement(Date dateMouvement, Compte compte, double somme, String lib, boolean debit) {
        this.id = -1;
        this.dateMouvement = dateMouvement;
        this.compte = compte;
        this.somme = somme;
        this.lib = lib;
        this.debit = debit;
    }

    public Mouvement(int id, Date dateMouvement, Compte compte, double somme, String lib, boolean debit) {
        this.id = id;
        this.dateMouvement = dateMouvement;
        this.compte = compte;
        this.somme = somme;
        this.lib = lib;
        this.debit = debit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }
}

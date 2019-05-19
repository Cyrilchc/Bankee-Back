package com.iut.metz.bankee.back.metier.objet;

import java.util.*;

import com.iut.metz.bankee.back.metier.process.ListComptesProcess;
import javax.persistence.*;

/**
 * Classe m�tier Client Repr�sente un client de la banque
 */
@Entity
@Table(name = "client")
public class Client {

    // Attributs de la classe
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "numero_client")
    private String numeroClient;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "nom")
    private String nom;
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "appartient",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_compte"))
    private List<Compte> comptes;

    public int getId() {
        return id;
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNom() {
        return nom;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public String getPassword() {
        return password;
    }

    public Client(String numeroClient, String adresse, String nom, String password) {
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.password = password;
        this.comptes = new ArrayList<>();
    }

    public Client() {
    }

    /**
     * Constructeur de la classe Client avec id technique et liste de comptes
     */
    public Client(int id, String numeroClient, String adresse, String nom, String password, List<Compte> comptes) {
        this.id = id;
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.comptes = comptes;
        this.password = password;
        if (comptes == null) {
            this.comptes = new ArrayList<>();
        }
    }

    public int nbComptes() {
        return this.comptes.size();
    }

    /**
     * Ajoute un compte au client de la banque
     * @return true si le compte a été ajouté
     */
    public boolean ajouterCompte(Compte... comptes) {
        new ListComptesProcess().addCompte(this.comptes, comptes);
        return true;
    }
}

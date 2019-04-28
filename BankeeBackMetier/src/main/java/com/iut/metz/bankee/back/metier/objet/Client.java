package com.iut.metz.bankee.back.metier.objet;

import java.util.*;

import com.iut.metz.bankee.back.metier.process.ListComptesProcess;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe m�tier Client 
 * Repr�sente un client de la banque
 */
@Entity(name = "client")
public class Client implements Serializable {

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
    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;

    // Accesseurs de la classe
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the comptes
     */
    public List<Compte> getComptes() {
        return comptes;
    }

    public Client() {
    }
    
    /**
     * Constructeur de la classe Client par d�faut
     *
     * @param numeroClient
     * @param adresse
     * @param nom
     */
    public Client(String numeroClient, String adresse, String nom) {
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Constructeur de la classe Client avec id technique et liste de comptes
     *
     * @param id
     * @param numeroClient
     * @param adresse
     * @param nom
     * @param comptes
     */
    public Client(int id, String numeroClient, String adresse, String nom, List<Compte> comptes) {
        this.id = id;
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.comptes = comptes;
        if (comptes == null) {
            this.comptes = new ArrayList<>();
        }
    }

    // M�thodes de la classe
    public int nbComptes() {
        return this.comptes.size();
    }

    /**
     * Ajoute un compte au client de la banque
     *
     * @param comptes
     * @return true si le compte a �t� ajout�
     */
    public boolean ajouterCompte(Compte... comptes) {
        new ListComptesProcess().addCompte(this.comptes, comptes);
        return true;
    }

}

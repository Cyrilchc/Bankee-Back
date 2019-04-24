package metier;

import java.util.ArrayList;
import java.util.List;
import metier.exception.ClientException;
import static metier.exception.utils.ClientExceptionUtils.COMPTES_NULL;
import static metier.exception.utils.ClientExceptionUtils.COMPTE_NULL;

/**
 * Classe métier Client Représente un client de la banque
 *
 * @author Loïc NOEL
 */
public class Client {

    // Attributs de la classe
    private int id;
    private String numeroClient;
    private String adresse;
    private String nom;
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

    /**
     * Constructeur de la classe Client par défaut
     *
     * @param numeroClient
     * @param adresse
     * @param nom
     */
    public Client(String numeroClient, String adresse, String nom) {
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.comptes = new ArrayList<Compte>();
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
    public Client(int id, String numeroClient, String adresse, String nom, List<Compte> comptes) throws ClientException {
        if (comptes == null) {
            throw new ClientException(COMPTES_NULL);
        }
        this.id = id;
        this.numeroClient = numeroClient;
        this.adresse = adresse;
        this.nom = nom;
        this.comptes = new ArrayList<Compte>();
    }

    // Méthodes de la classe
    public int nbComptes() {
        return this.comptes.size();
    }

    /**
     * Ajoute un compte au client de la banque
     *
     * @param compte
     * @return true si le compte a été ajouté
     * @throws metier.exception.ClientException
     */
    public boolean ajouterCompte(Compte compte) throws ClientException {
        if (this.comptes == null) {
            this.comptes = new ArrayList<Compte>();
        } else if (compte == null) {
            throw new ClientException(COMPTE_NULL);
        }
        this.comptes.add(compte);
        return true;
    }

}

package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.ClientExceptionUtils.*;

import com.iut.metz.bankee.back.metier.manager.*;
import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.process.ConversionProcess;

/**
 * Classe Métier Banque
 * Représente la gestion de la banque
 * un helper pour gerer la banque
 */
public class Banque {

    /**
     * @param numeroCompte le numero de conpte que l'on veut consulter
     * @return le compte que l'on veux consulter, null sinon
     */
    public Compte consultation(String numeroCompte) {
        try {
             return CompteManager.getInstance().getCompteByNumCompte(numeroCompte);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param numeroClient le numero de client que l'on veut consulter
     * @return le client que l'on veux consulter, null sinon
     */
    public Client consultationClient(String numeroClient) {
        try {
            return ClientManager.getInstance().getByNumClient(numeroClient);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param somme le montant en euro
     * @param monnaie la monnaie dans laquel le montant doit être converti
     * @return le montant dans la monnaie demandé
     * @throws MetierException est lancé si les donnée ne sont pas valid (ex. somme = -1)
     */
    public Montant conversionFromEuro(double somme, Monnaie monnaie) throws MetierException {
        return new ConversionProcess().fromEuro(somme, monnaie);
    }

    /**
     * @param montant le montant dans la mmonnaie donné
     * @return le montant en euro
     * @throws MetierException est lancé si le montant est null
     */
    public Montant conversionToEuro(Montant montant) throws MetierException {
        return new ConversionProcess().toEuro(montant);
    }

    /**
     * ajoute un compte à un client
     */
    public Client ouvertureCompte(String numeroClient) {
        Client client = consultationClient(numeroClient);
        return client;
    }

    /**
     * récupère un compte et le crédite au montant donné
     */
    public boolean depot(String numeroCompte, Montant montant) throws MetierException {
        return effectuerOpertation(numeroCompte, montant, compte -> compte.crediter(montant));
    }

    /**
     * récupère un compte et le débite au montant donné
     */
    public boolean retrait(String numeroCompte, Montant montant) throws MetierException {
        return effectuerOpertation(numeroCompte, montant, compte -> compte.debiter(montant));
    }

    private boolean effectuerOpertation(String numeroCompte, Montant montant, ConsumerVerifie<Compte> operationAFaire) throws MetierException {
        Compte compte = consultation(numeroCompte);
        if (compte != null) {
            if (compte.getId() != -1){
                operationAFaire.accept(compte);
            } else {
                throw new MetierException(COMPTE_INEXISTANT);
            }
        } else {
            throw new ClientException(OPERATION_IMPOSIBLE);
        }
        return true;
    }

    @FunctionalInterface
    private interface ConsumerVerifie<T> {
        void accept(T t) throws MetierException;
    }
}

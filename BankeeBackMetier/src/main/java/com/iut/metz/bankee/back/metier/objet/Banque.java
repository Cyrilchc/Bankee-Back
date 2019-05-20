package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.ClientExceptionUtils.*;
import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.MONNAIE_PAR_DEFAUT;

import com.iut.metz.bankee.back.metier.manager.*;
import com.iut.metz.bankee.back.metier.objet.builder.ClientBuilder;
import com.iut.metz.bankee.back.metier.objet.builder.MouvementBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.*;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.process.ConversionProcess;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Classe M�tier Banque
 * Repr�sente la gestion de la banque
 * un helper pour gerer la banque
 */
public class Banque {

    /**
     * @param numeroCompte le numero de conpte que l'on veut consulter
     * @return le compte que l'on veux consulter, null sinon
     */
    public Compte consultation(String numeroCompte) {
        try {
             Compte compte = CompteManager.getInstance().getCompteByNumCompte(numeroCompte);
             purgeMovement(compte.getMouvements());
             return compte;
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
            Client client = ClientManager.getInstance().getByNumClient(numeroClient);
            client.getComptes().forEach(compte -> purgeMovement(compte.getMouvements()));
            return client;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param somme le montant en euro
     * @param monnaie la monnaie dans laquel le montant doit �tre converti
     * @return le montant dans la monnaie demand�
     * @throws MetierException est lanc� si les donn�e ne sont pas valid (ex. somme = -1)
     */
    public Montant conversionFromEuro(double somme, Monnaie monnaie) throws MetierException {
        return new ConversionProcess().fromEuro(somme, monnaie);
    }

    /**
     * @param montant le montant dans la mmonnaie donn�
     * @return le montant en euro
     * @throws MetierException est lanc� si le montant est null
     */
    public Montant conversionToEuro(Montant montant) throws MetierException {
        return new ConversionProcess().toEuro(montant);
    }

    /**
     * Crée un client
     */
    public Client creationClient(Personne personne) {
        String numeroClient = Long.toString(Date.from(Instant.now()).getTime());
        Client client = new ClientBuilder()
                .addNumeroClient(numeroClient)
                .addAdresse(personne.getAdresse())
                .addNomm(personne.getNom())
                .addPassword("1234")
                .build();
        client = ClientManager.getInstance().create(client);
        return client;
    }

    public void doVirement(Virement virement) throws MetierException {
        CompteManager compteManager = CompteManager.getInstance();
        MouvementManager mouvementManager = MouvementManager.getInstance();
        Compte donneur = compteManager.getCompteByNumCompte(virement.getDonneur());
        Compte receveur = CompteManager.getInstance().getCompteByNumCompte(virement.getReceveur());
        Montant montant = new Montant(virement.getSomme(), MONNAIE_PAR_DEFAUT);
        donneur.debiter(montant);
        receveur.crediter(montant);
        Date date = Date.from(Instant.now());
        Mouvement donneurMov = new MouvementBuilder()
                .addIsDebit(true)
                .addCompte(donneur)
                .addDateMouvement(date)
                .addLib(virement.getReason())
                .addSomme(conversionToEuro(montant).getMontant())
                .build();
        Mouvement receveurMov = new MouvementBuilder()
                .addIsDebit(false)
                .addCompte(receveur)
                .addDateMouvement(date)
                .addLib(virement.getReason())
                .addSomme(conversionToEuro(montant).getMontant())
                .build();
        mouvementManager.create(donneurMov, receveurMov);
        compteManager.update(donneur, receveur);
    }

    /**
     * r�cup�re un compte et le cr�dite au montant donn�
     */
    public boolean depot(String numeroCompte, Montant montant) throws MetierException {
        return effectuerOpertation(numeroCompte, montant, compte -> compte.crediter(montant));
    }

    /**
     * r�cup�re un compte et le d�bite au montant donn�
     */
    public boolean retrait(String numeroCompte, Montant montant) throws MetierException {
        return effectuerOpertation(numeroCompte, montant, compte -> compte.debiter(montant));
    }

    public List<Mouvement> getMovementByCompte(String numCompte) {
        Compte compte = CompteManager.getInstance().getCompteByNumCompte(numCompte);
        return MouvementManager.getInstance().getByCompte(compte);
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

    public void purgeMovement(List<Mouvement> mouvements) {
        mouvements.forEach(mouvement -> mouvement.setCompte(null));
    }

    @FunctionalInterface
    private interface ConsumerVerifie<T> {
        void accept(T t) throws MetierException;
    }
}

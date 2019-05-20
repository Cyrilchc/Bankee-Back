package com.iut.metz.bankee.back.metier.objet;

import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.MetierException;
import com.iut.metz.bankee.back.metier.objet.exception.MontantException;
import com.iut.metz.bankee.back.metier.process.MontantProcess;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "compte")
@DiscriminatorFormula("case when decouvert_autorise is null then 'no' when decouvert_autorise is not null then 'yes' end ")
public class Compte implements Serializable {
    public Compte() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "solde")
    private double solde;
    @Column(name = "numero_compte")
    private String numeroCompte;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compte")
    private List<Mouvement> mouvements;

    public Compte(int id, double solde, String numeroCompte, List<Mouvement> mouvements) {
        this.id = id;
        this.solde = solde;
        this.numeroCompte = numeroCompte;
        this.mouvements = mouvements;
    }

    public Compte(double solde, String numeroCompte, List<Mouvement> mouvements) {
        this(-1, solde, numeroCompte, mouvements);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * le solde est en euro
     *
     * @return le solde actuel du compte
     */
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) throws MontantException {
        this.solde = solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public boolean debiter(Montant montant) throws MetierException {
        if (new MontantProcess().isValid(montant)) {
            doDebiter(montant);
        }
        return true;
    }

    protected void doDebiter(Montant montant) throws MontantException {
        return;
    }

    public boolean crediter(Montant montant) throws MetierException {
        if (new MontantProcess().isValid(montant)) {
            solde += (montant.getMontant() * montant.getMonnaie().getValeurEnEuro());
        }
        return true;
    }

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return id == compte.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solde, numeroCompte);
    }

    @Override
    public String toString() {
        return "Compte{" + "id=" + id + ", solde=" + solde + ", numeroCompte='" + numeroCompte + '\'' + '}';
    }
}

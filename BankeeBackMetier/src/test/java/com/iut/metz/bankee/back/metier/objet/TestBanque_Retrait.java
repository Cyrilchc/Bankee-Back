package com.iut.metz.bankee.back.metier.objet;

import static com.iut.metz.bankee.back.metier.objet.utils.MockUtils.mockCompteDao;
import static com.iut.metz.bankee.back.metier.utils.CurrencyUtils.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.iut.metz.bankee.back.metier.manager.*;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;
import com.iut.metz.bankee.back.metier.objet.currency.Montant;
import com.iut.metz.bankee.back.metier.objet.exception.*;
import com.iut.metz.bankee.back.metier.objet.utils.BiConsumerEx;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompteManager.class)
public class TestBanque_Retrait {
    private BiConsumerEx<String, Montant> opperation = (num, montant) -> new Banque().retrait("test", montant);

    @Test
    public void testRetrait_casMontantEuroPositif() throws MetierException {
        new TestBanque_Operation(0,2).casMontantEuroPositif(opperation);
    }

    @Test
    public void testRetrait_casMontantDollardPositif() throws MetierException {
        new TestBanque_Operation(0, 2*DOLLARD.getValeurEnEuro()).casMontantDollardPositif(opperation);
    }

    @Test(expected = MetierException.class)
    public void testRetrait_casCompteInexistant() throws MetierException {
        new TestBanque_Operation().casCompteInexistant(opperation);
    }

    @Test(expected = ClientException.class)
    public void testRetrait_casCompteNull() throws MetierException {
        new TestBanque_Operation().casCompteNull(opperation);
    }

    @Test(expected = MetierException.class)
    public void testRetrait_casMontantNull() throws MetierException {
        new TestBanque_Operation().casMontantNull(opperation);
    }

    @Test(expected = MontantException.class)
    public void testRetrait_casMontantNegatif() throws MetierException {
        new TestBanque_Operation().casMontantNegatif(opperation);
    }

    @Test(expected = MontantException.class)
    public void testRetrait_casMontantVaut0() throws MetierException {
        new TestBanque_Operation().casMontantVaut0(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieVaut0() throws MetierException {
        new TestBanque_Operation().casMonnaieVaut0(opperation);
    }

    @Test(expected = MetierException.class)
    public void testRetrait_casMonnaieNull() throws MetierException {
        new TestBanque_Operation().casMonnaieNull(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieNegatif() throws MetierException {
        new TestBanque_Operation().casMonnaieNegatif(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieNomVide() throws MetierException {
        new TestBanque_Operation().casMonnaieNomVide(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieNomNull() throws MetierException {
        new TestBanque_Operation().casMonnaieNomNull(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieSymboleVide() throws MetierException {
        new TestBanque_Operation().casMonnaieSymboleVide(opperation);
    }

    @Test(expected = MonnaieException.class)
    public void testRetrait_casMonnaieSymboleNull() throws MetierException {
        new TestBanque_Operation().casMonnaieSymboleNull(opperation);
    }

    @Test
    public void testConsultationClient_compteAvecDecouvertPasse() throws MetierException {
        //give
        double expected = 2;
        Compte compte = new CompteBuilder().addId(1).addSolde(0).addDecouvert(3).build();
        mockCompteDao(compte);
        Montant montant = new Montant(expected, MONNAIE_PAR_DEFAUT);
        //when
        opperation.accept("test", montant);
        //then
        assertEquals(-2, compte.getSolde(), 0);
    }

    @Test(expected = MetierException.class)
    public void testConsultationClient_compteAvecDecouvertPassePas() throws MetierException {
        //give
        double expected = 2;
        Compte compte = new CompteBuilder().addId(1).addSolde(0).addDecouvert(1).build();
        mockCompteDao(compte);
        Montant montant = new Montant(expected, MONNAIE_PAR_DEFAUT);
        //when
        opperation.accept("test", montant);
        //then
        fail();
    }
}

package com.iut.metz.bankee.back.metier.process;

import com.iut.metz.bankee.back.metier.objet.Mouvement;
import com.iut.metz.bankee.back.metier.objet.builder.MouvementBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.Instant;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestListMouvementProcess {
    private static List<Mouvement> addMouv(int... ids) {
        List<Mouvement> mouvements = new ArrayList<>();
        Arrays.stream(ids).forEach(id -> {
            Mouvement mouvement = new MouvementBuilder().addId(id).build();
            mouvements.add(mouvement);
        });
        return mouvements;
    }
    private static Date date = Date.from(Instant.now());

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { addMouv(), addMouv(), addMouv() },
                { addMouv(), null, addMouv() },
                { null, addMouv(), null },
                { null, null, null },
                { addMouv(1), addMouv(), addMouv(1) },
                { addMouv(1), null, addMouv(1) },
                { addMouv(1), addMouv(1), addMouv(1) },
                { addMouv(1), addMouv(1,2), addMouv(1, 2) },
                { addMouv(1,2), addMouv(1), addMouv(1, 2) }
        });
    }
    private List<Mouvement> aEnrichir;
    private List<Mouvement> nouveau;
    private List<Mouvement> expected;

    public TestListMouvementProcess(List<Mouvement> aEnrichir, List<Mouvement> nouveau, List<Mouvement> expected) {
        this.aEnrichir = aEnrichir;
        this.nouveau = nouveau;
        this.expected = expected;
    }

    @Test
    public void test() {
        List<Mouvement> actual = new ListMouvementProcess().addMouvement(aEnrichir, nouveau);
        if (expected == null) {
            assertNull(actual);
        } else {
            assertTrue(expected.size() == actual.size() && expected.containsAll(actual));
        }
    }

}

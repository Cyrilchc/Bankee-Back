package com.iut.metz.bankee.back.metier.process;

import java.util.*;

import com.iut.metz.bankee.back.metier.objet.Compte;

public class ListComptesProcess {

  public List<Compte> addCompte(List<Compte> list, Compte... comptes) {
    if (comptes != null) {
      return addCompte(list, Arrays.asList(comptes));
    }
    return list;
  }

  public List<Compte> addCompte(List<Compte> listAEnrichire, List<Compte> comptes) {
    if (comptes != null) {
      comptes.forEach( compte -> {
        if (compte != null && !contains(listAEnrichire,compte)) {
          listAEnrichire.add(compte);
        }
      });
    }
    return listAEnrichire;
  }
  
  public boolean contains(List<Compte> comptes, Compte compte) {
      return comptes.stream().anyMatch((c) -> (c.getId()==compte.getId()));
  } 
}

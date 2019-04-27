package com.iut.metz.bankee.back.metier.process;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import com.iut.metz.bankee.back.metier.objet.Compte;
import com.iut.metz.bankee.back.metier.objet.builder.CompteBuilder;

public class TestListComptesProcess {
  ListComptesProcess process = new ListComptesProcess();
  List<Compte> expected;
  List<Compte> res;

  @Before
  public void init() {
    expected  = new ArrayList<>();
    res       = new ArrayList<>();
  }

  @Test
  public void testAddCompte_1Compte() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    expected.add(compte1);
    //when
    res = process.addCompte(res, compte1);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteSimultanes() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, compte1, compte2);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteSepare() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, compte1);
    res = process.addCompte(res, compte2);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_Tableau() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    Compte[] comptes = new Compte[]{compte1, compte2};
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_List() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, expected);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteSepareEtUnNull() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    Compte compte3 = null;
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, compte1);
    res = process.addCompte(res, compte2);
    res = process.addCompte(res, compte3);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteEtUnNullSimultane() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    Compte compte3 = null;
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, compte1, compte2, compte3);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteEtUnNullTableau() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    Compte compte3 = null;
    Compte[] comptes = new Compte[]{compte1, compte2, compte3};
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2CompteEtUnNullList() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(2).build();
    Compte compte3 = null;
    List<Compte> comptes = Arrays.asList(compte1, compte2, compte3);
    expected.add(compte1);
    expected.add(compte2);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2MemeCompteSepare() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    expected.add(compte1);
    //when
    res = process.addCompte(res, compte1);
    res = process.addCompte(res, compte1);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2MemeCompteSimultane() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    expected.add(compte1);
    //when
    res = process.addCompte(res, compte1, compte1);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2MemeCompteTableau() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte[] comptes = new Compte[]{compte1, compte1};
    expected.add(compte1);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2MemeCompteList() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    List<Compte> comptes = Arrays.asList(compte1, compte1);
    expected.add(compte1);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }

  @Test
  public void testAddCompte_2MemeCompteMaisPasMemeValeurMemoire() {
    //give
    Compte compte1 = new CompteBuilder().addId(1).build();
    Compte compte2 = new CompteBuilder().addId(1).build();
    List<Compte> comptes = Arrays.asList(compte1, compte2);
    expected.add(compte1);
    //when
    res = process.addCompte(res, comptes);
    //then
    assertEquals(expected, res);
  }
}

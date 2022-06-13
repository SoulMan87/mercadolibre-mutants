package com.soulrebel.ml.service;

import com.soulrebel.ml.enums.StatusCode;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneticistServiceTest {

    @Test
    public void instanceGeneticist() {
        GeneticistService geneticist = new GeneticistService();
        assertNotNull(geneticist);
    }

    @Test
    public void geneticistVerifyHumanDnaIsMutant() {
        String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA"};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.SUCCESS,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyHumanDnaIsNotMutant() {
        String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.FORBIDDEN,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyIfHumanDnaIsEmpty() {
        String[] dna = {""};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.ERROR,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyIfHumanDnaIsNull() {
        String[] dna = {""};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.ERROR,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyIfHumanDnaIsSquare() {
        String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA"};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.SUCCESS,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyIfHumanDnaIsNotSquare() {
        String[] dna = {"AAAA", "CAAT", "CAAT", "AAGA", "AAAA"};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.ERROR,geneticist.verifyIfHumanDnaIsMutant(dna));
    }

    @Test
    public void geneticistVerifyIfMoleculesOfHumanDnaAreOnlyString() {
        String[] dna = {"AAA1", "C2AT", "C*AT", "AAGA"};
        GeneticistService geneticist = new GeneticistService();
        assertEquals(StatusCode.ERROR,geneticist.verifyIfHumanDnaIsMutant(dna));
    }
}

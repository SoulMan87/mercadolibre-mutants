package com.soulrebel.ml.logic;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExperimentLogicTest {

    @Test
    public void checkDnaIsNull() {
        assertFalse(ExperimentLogic.validateMoleculesHumanDna(null));
    }

    @Test
    public void checkDnaIsEmpty() {
        String[] dna = {""};
        assertTrue(ExperimentLogic.validateMoleculesHumanDna(dna));
    }

    @Test
    public void checkDnaIsNotEmpty() {
        String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
        assertTrue(ExperimentLogic.validateMoleculesHumanDna(dna));
    }

    @Test
    public void checkDnaHasMoleculesValid() {
        String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
        assertTrue(ExperimentLogic.validateMoleculesHumanDna(dna));
    }

    @Test
    public void checkDnaHasMoleculesInvalid() {
        String[] dna = {"RTRC", "GTGR", "TRCT", "AAGR"};
        assertTrue(ExperimentLogic.validateMoleculesHumanDna(dna));
    }

    @Test
    public void checkHumanDnaChainIsSquare() {
        String[] dna = {"RTRC", "GTGR", "TRCT", "AAGR"};
        assertTrue(ExperimentLogic.validateHumanDnaIsSquare(dna));
    }

    @Test
    public void checkHumanDnaChainIsNotSquare() {
        String[] dna = {"RTRC", "GTGRASA", "TRCT", "AAGR"};
        assertFalse(ExperimentLogic.validateHumanDnaIsSquare(dna));
    }
    @Test
    public void isMutant() {
        String[] dna = {"AAAA", "AAAA", "AAAA", "CAGT"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkHorizontallyIfHumanDnaIsNotMutant() {
        String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};
        assertFalse(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkHorizontallyIfHumanDnaIsMutant() {
        String[] dna = {"AAAA", "CCCC", "TTTT", "GGGG"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreMoreOfOneSecuencesMutantDnaHorizontally() {
        String[] dna = {"AAAA", "CCCC", "TTTT", "GGGG"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreLessOfOneSecuencesMutantDnaHorizontally() {
        String[] dna = {"AAAA", "CAGT", "CAGT", "CAGT"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreNotSecuencesMoleculesMutantDnaHorizontally() {
        String[] dna = {"CAGT", "AGTC", "GCAT", "AGTC"};
        assertFalse(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreMoreOfOneSecuencesMutantDnaVertically() {
        String[] dna = {"ACTG", "ACTG", "ACTG", "ACTG"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreLessOfOneSecuencesMutantDnaVertically() {
        String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
       assertFalse(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreNotSecuencesMoleculesMutantDnaVertically() {
        String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
        assertFalse(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreMoreOfOneSecuencesMutantDnaDiagonally() {
        String[] dna = {"TCAGT", "CTGTA", "AGTAC", "GTATT", "AACTA"};
        assertTrue(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreLessOfOneSecuencesMutantDnaDiagonally() {
        String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
        assertFalse(ExperimentLogic.isMutant(dna));
    }

    @Test
    public void checkIfThereAreNotSecuencesMoleculesMutantDnaDiagonally() {
        String[] dna = {"AAAA", "CCGT", "CAGT", "CAGT"};
        assertFalse(ExperimentLogic.isMutant(dna));
    }

}

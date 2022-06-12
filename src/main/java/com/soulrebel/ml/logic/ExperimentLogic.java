package com.soulrebel.ml.logic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

@Slf4j
public class ExperimentLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentLogic.class);

    private static final String MUTANT_SEQUENCES = "AAAA|CCCC|TTTT|GGGG";
    private static final int LONG_MUTANT_GENE = 3;


    public static boolean isMutant(String[] dna) {
        LOGGER.info("This method validates if a human has a mutant dna.");

        Pattern pattern = Pattern.compile(MUTANT_SEQUENCES);

        int horizontals = countSequencesOfMutantDnaHorizontally(dna, pattern);
        int verticals = countSequencesOfMutantDnaVertically(dna, pattern);
        int diagonalsFromRightToLeft = countSequencesOfMutantDnaDiagonallyFromRightToLeft(dna, pattern);
        int diagonalsFromLeftToRight = countSequencesOfMutantDnaDiagonallyFromLeftToRight(dna, pattern);

        return (horizontals + verticals + diagonalsFromRightToLeft + diagonalsFromLeftToRight ) > 1;

    }

    public static boolean validateMoleculesHumanDna(String[] dna) {
        LOGGER.info("This method validates if a human dna has only molecules valid.");

        if (dna != null && dna.length > 0) {
            for (String item : dna) {
                if (Pattern.compile("AaCcGgTt").matcher(item).find()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateHumanDnaIsSquare(String[] dna){
        LOGGER.info("This method validates if a human dna is square.");

        int numberOfMolecules = dna.length;

        for (String genome: dna) {
            if (numberOfMolecules != genome.length()){
                return false;
            }
        }
        return true;
    }

    private static int countSequencesOfMutantDnaDiagonallyFromLeftToRight(String[] dna, Pattern pattern) {
        LOGGER.info("Counting the sequences of mutants dna from left to right.");

        int diagonals = 0;

        for (int i = 0; i < dna.length - LONG_MUTANT_GENE; i++) {
            for (int j = dna.length; j > LONG_MUTANT_GENE; j--) {
                String diagonalBuilder = String.valueOf(dna[i].charAt(j - 1)) +
                        dna[i + 1].charAt(j - 2) +
                        dna[i + 2].charAt(j - 3) +
                        dna[i + 3].charAt(j - 4);

                diagonals += sequenceIsMatched(pattern, diagonalBuilder);
            }
        }
        return diagonals;
    }

    private static int countSequencesOfMutantDnaDiagonallyFromRightToLeft(String[] dna, Pattern pattern) {
        LOGGER.info("Counting the sequences of mutants dna from right to left.");

        int diagonals = 0;

        for (int i = 0; i < dna.length - LONG_MUTANT_GENE; i++) {
            for (int j = 0; j < dna.length - LONG_MUTANT_GENE; j++) {
                String diagonalBuilder = String.valueOf(dna[i].charAt(j)) +
                        dna[i + 1].charAt(j + 1) +
                        dna[i + 2].charAt(j + 2) +
                        dna[i + 3].charAt(j + 3);

                diagonals += sequenceIsMatched(pattern, diagonalBuilder);
            }
        }
        return diagonals;
    }

    private static int countSequencesOfMutantDnaVertically(String[] dna, Pattern pattern) {
        LOGGER.info("Counting the sequences of mutants dna from vertical.");

        int verticals = 0;
        for (int i = 0; i < dna.length; i++) {
            StringBuilder verticalBuilder = new StringBuilder();
            for (String s : dna) {
                verticalBuilder.append(s.charAt(i));
            }
            verticals += sequenceIsMatched(pattern, verticalBuilder.toString());
        }
        return verticals;
    }

    private static int countSequencesOfMutantDnaHorizontally(String[] dna, Pattern pattern) {
        LOGGER.info("Counting the sequences of mutants dna from horizontal.");

        int horizontals = 0;
        for (String genome : dna) {
            horizontals += sequenceIsMatched(pattern, genome);
        }
        return horizontals;
    }

    private static int sequenceIsMatched(Pattern pattern, String genome) {
        return pattern.matcher(genome).find() ? 1 : 0;
    }
}

package com.soulrebel.ml.service;

import com.soulrebel.ml.enums.StatusCode;
import com.soulrebel.ml.logic.ExperimentLogic;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneticistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneticistService.class);

    @Cacheable(value = "verify_if_human_dna_is_mutant", key = "#dna")
    public StatusCode verifyIfHumanDnaIsMutant(String[] dna) {
        LOGGER.info("Verifying if a human dna is mutant");

        if (!ExperimentLogic.validateMoleculesHumanDna(dna)) {
            return StatusCode.ERROR;
        }

        if (!ExperimentLogic.validateHumanDnaIsSquare(dna)) {
            return StatusCode.ERROR;
        }

        if (!ExperimentLogic.isMutant(dna)) {
            return StatusCode.FORBIDDEN;
        }
        return StatusCode.SUCCESS;
    }
}

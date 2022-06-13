package com.soulrebel.ml.controller;

import com.soulrebel.ml.enums.StatusCode;
import com.soulrebel.ml.model.DnaResponse;
import com.soulrebel.ml.service.GeneticistService;
import com.soulrebel.ml.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MutantController implements IMutantController {

    private final StatisticsService statisticsService;

    private final GeneticistService geneticistService;

    @Override
    public ResponseEntity<String> mutant(DnaResponse dnaResponse) {

        StatusCode statusCode = geneticistService.verifyIfHumanDnaIsMutant(dnaResponse.getDna());

        if (statusCode == StatusCode.ERROR) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        statusCode = statisticsService.saveDnaConcrete(dnaResponse, statusCode);

        if (statusCode == StatusCode.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

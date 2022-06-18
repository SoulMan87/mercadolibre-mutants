package com.soulrebel.ml.controller;


import com.soulrebel.ml.model.DnaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
public interface IMutantController {

    @PostMapping(value = "/mutants", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> mutant(@RequestBody @Validated DnaResponse dnaResponse);
}

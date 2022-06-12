package com.soulrebel.ml.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
public class DnaResponse {

    private String[] dna;

}

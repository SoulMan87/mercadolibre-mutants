package com.soulrebel.ml.model;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {

    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;

}

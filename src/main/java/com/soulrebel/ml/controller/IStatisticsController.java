package com.soulrebel.ml.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
public interface IStatisticsController {

    @GetMapping(value = "/stats", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    String stats();
}

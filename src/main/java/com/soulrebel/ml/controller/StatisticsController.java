package com.soulrebel.ml.controller;


import com.soulrebel.ml.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticsController implements IStatisticsController{

    private final StatisticsService service;

    @Override
    public String stats() {
        return service.getStatisticsDnaConcrete();
    }
}

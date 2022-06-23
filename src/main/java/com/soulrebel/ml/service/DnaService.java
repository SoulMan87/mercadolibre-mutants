package com.soulrebel.ml.service;

import com.google.gson.Gson;
import com.soulrebel.ml.entity.Dna;
import com.soulrebel.ml.model.StatisticsResponse;
import com.soulrebel.ml.repository.DnaRepository;
import com.soulrebel.ml.repository.GenericRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;


/**
 * Con este servicio se obtione la data de la base de datos
 *
 * @author Jonathan Hinestroza
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DnaService implements GenericRepository<Dna> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DnaService.class);

    private final DnaRepository repository;

    @Override
    public void save(Dna entity) {
        LOGGER.info("DNA is creating... Saved");
        repository.save(entity);

    }

    @Override
    public String findAll() {

        LOGGER.info("DNA is creating... Saved");

        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setCountMutantDna(repository.counterMutants());
        statisticsResponse.setCountHumanDna(repository.counterNotMutants());

        return getResponse(statisticsResponse);
    }

    private String getResponse(StatisticsResponse statisticsResponse) {
        Double ratio;
        String response;
        if (statisticsResponse.getCountMutantDna() > 0 && statisticsResponse.getCountHumanDna() > 0) {
            ratio = statisticsResponse.getCountMutantDna().doubleValue() / statisticsResponse.getCountHumanDna();
            String ratioString = new DecimalFormat("#.##").format(ratio);
            statisticsResponse.setRatio(Double.valueOf(ratioString));
        } else {
            statisticsResponse.setRatio(0.0);
        }
        response = new Gson().toJson(statisticsResponse);

        return response;
    }
}

package com.soulrebel.ml.service;

import com.google.gson.Gson;
import com.soulrebel.ml.entity.Dna;
import com.soulrebel.ml.enums.StatusCode;
import com.soulrebel.ml.model.DnaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Servicio que reconoce los debidos pasos que se necesitan para guardar un dna.
 *
 * @author Jonathan Hinestroza
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    private final DnaService service;

    @Cacheable(value = "save_dna_concrete_cache", key = "#dnaResponse+'-'+#statusCode")
    public StatusCode saveDnaConcrete(DnaResponse dnaResponse, StatusCode statusCode) {
        LOGGER.info("DNA is creating... Saved");

        try {
            Dna dna = new Dna();
            dna.setDna(new Gson().toJson(dnaResponse.getDna()));
            boolean isMutant = statusCode == StatusCode.SUCCESS;
            dna.setIsMutant(isMutant);

            service.save(dna);
        } catch (Exception e) {
            statusCode = StatusCode.ERROR;
            LOGGER.error("An error has occurred: " + e.getMessage());
        }

        return statusCode;
    }

    @Cacheable(value = "get_statistics_cache")
    public String getStatisticsDnaConcrete() {
        LOGGER.info("Getting the dna's statistics");
        return service.findAll();
    }


}

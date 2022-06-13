package com.soulrebel.ml.service;

import com.soulrebel.ml.entity.Dna;
import com.soulrebel.ml.enums.StatusCode;
import com.soulrebel.ml.model.DnaResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private DnaService dnaService;

    @Test
    public void validateSaveDnaStatistics() {
        String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};

        Dna entity = new Dna();
        entity.setDna(Arrays.toString(dna));
        entity.setIsMutant(false);

        Mockito.doNothing().when(dnaService).save(entity);

        StatusCode statusCode = StatusCode.SUCCESS;
        DnaResponse response = new DnaResponse();
        response.setDna(dna);

        Assert.assertEquals(StatusCode.SUCCESS, statisticsService.saveDnaConcrete(response, statusCode));
    }

    @Test
    public void verifyStatisticalDnaVerified() {

        String responseExpected = "{\"countMutantDna\":40, \"countHumanDna\":100, \"ratio\":0.4}";

        Mockito.when(dnaService.findAll()).thenReturn(responseExpected);

        Assert.assertSame(responseExpected, statisticsService.getStatisticsDnaConcrete());
    }

}

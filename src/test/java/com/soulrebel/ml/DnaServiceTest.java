package com.soulrebel.ml;

import com.soulrebel.ml.entity.Dna;
import com.soulrebel.ml.repository.DnaRepository;
import com.soulrebel.ml.service.DnaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DnaServiceTest {

    @InjectMocks
    private DnaService service;

    @Mock
    private DnaRepository repository;

    @Test
    public void validateDnaSave() {
        try {
            String[] dna = {"ATGC", "CAGT", "TTCT", "AGAA"};

            Dna entity = new Dna();
            entity.setDna(Arrays.toString(dna));
            entity.setIsMutant(false);

            Mockito.when(repository.save(entity)).thenReturn(entity);

            service.save(entity);
        } catch (Exception e) {
            Assert.fail("There's a problem in this method");
        }
    }

    @Test
    public void validateDnaFindAllWithDefaultResponse() {
        try {
            String dnaExpected = "{\"countMutantDna\":0,\"countHumanDna\":0,\"ratio\":0.0}";

            Mockito.when(repository.counterMutants()).thenReturn(0L);
            Mockito.when(repository.counterNotMutants()).thenReturn(0L);

            Assert.assertEquals(dnaExpected, service.findAll());

        } catch (Exception e) {
            Assert.fail("There's a problem in this method.");
        }
    }

    @Test
    public void validateDnaFindAllWithRatioOne() {
        try {
            String dnaExpected = "{\"countMutantDna\":1,\"countHumanDna\":1,\"ratio\":1.0}";

            Mockito.when(repository.counterMutants()).thenReturn(1L);
            Mockito.when(repository.counterNotMutants()).thenReturn(1L);

            Assert.assertEquals(dnaExpected, service.findAll());

        } catch (Exception e) {
            Assert.fail("There's a problem in this method.");
        }
    }
}

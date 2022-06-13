package com.soulrebel.ml.controller;

import com.soulrebel.ml.enums.StatusCode;
import com.soulrebel.ml.model.DnaResponse;
import com.soulrebel.ml.model.DnaResponseMatcher;
import com.soulrebel.ml.service.GeneticistService;
import com.soulrebel.ml.service.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MutantControllerTest {

    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final String URI = "/mutants";

    private MockMvc mockMvc;

    @InjectMocks
    private MutantController mutantController;

    @Mock
    private StatisticsService statisticsService;

    @Mock
    private GeneticistService geneticistService;

    private final String[] dnaMutant = new String[]{"ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG"};

    private final String[] dnaHuman = new String[]{"ATGCCA", "CAGTGC", "TTCTGG", "AGAAGG", "CCCGTA", "TCGCTG"};

    private final String[] invalidDNA = new String[]{"ATGCCA", "CAGTGC", "TTCTGG", "AGAAGG", "TCGCTG"};

    private final String mockMutantBody = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";

    private final String mockHumanBody = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mutantController).build();
    }

    @Test
    public void testIfHumanDNAIsRecognizedAsMutant() throws Exception {

        DnaResponse response = new DnaResponse();
        response.setDna(dnaMutant);

        mockCheckIfHumanDnaIsMutant(response.getDna(), StatusCode.SUCCESS);
        mockSaveDnaVerified(response, StatusCode.SUCCESS, StatusCode.SUCCESS);


        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockMutantBody));

        MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.isTrue(content.isEmpty(), "Response body should be empty");
    }

    @Test
    public void testIfHumanDNAIsNotRecognizedAsMutant() throws Exception {

        DnaResponse response = new DnaResponse();
        response.setDna(dnaHuman);

        mockCheckIfHumanDnaIsMutant(response.getDna(), StatusCode.FORBIDDEN);

        mockSaveDnaVerified(response, StatusCode.FORBIDDEN, StatusCode.FORBIDDEN);

        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockMutantBody));

        MvcResult result = resultActions.andExpect(status().isForbidden()).andReturn();

        int codeStatus = result.getResponse().getStatus();

        Assert.isTrue(codeStatus == 403, "");
    }

    @Test
    public void testIfHumanDnaHasBadFormat() throws Exception {

        DnaResponse response = new DnaResponse();
        response.setDna(invalidDNA);

        mockCheckIfHumanDnaIsMutant(response.getDna(), StatusCode.ERROR);

        String mockInvalidBody = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"TCGCTG\"]}";
        ResultActions resultActions = mockMvc
                .perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockInvalidBody));

        MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.isTrue(content.isEmpty(), "Response body should be empty");
    }

    private void mockCheckIfHumanDnaIsMutant(String[] dna, StatusCode expectedResult) {
        Mockito.when(geneticistService.verifyIfHumanDnaIsMutant(dna)).thenReturn(expectedResult);
    }

    private void mockSaveDnaVerified(DnaResponse response, StatusCode statusCode, StatusCode expectedResult) {
        DnaResponseMatcher responseMatcher = new DnaResponseMatcher(response);
        Mockito.argThat(responseMatcher);
        Mockito.when(statisticsService.saveDnaConcrete(response, Mockito.eq(statusCode))).thenReturn(expectedResult);
    }
}

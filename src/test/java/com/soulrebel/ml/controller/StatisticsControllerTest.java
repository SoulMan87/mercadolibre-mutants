package com.soulrebel.ml.controller;

import com.soulrebel.ml.service.StatisticsService;
import org.junit.Assert;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatisticsControllerTest {

    private static final String URI = "/stats";

    private MockMvc mockMvc;

    @InjectMocks
    private StatisticsController controller;

    @Mock
    private StatisticsService service;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void validateStatsService()throws Exception {

        String statistics = "{\"countMutantDna\":0,\"countHumanDna\":0,\"ratio\":0.0}";

        Mockito.when(service.getStatisticsDnaConcrete()).thenReturn(statistics);

        ResultActions resultActions = mockMvc.perform(get(URI));

        MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(statistics, content);
    }
}

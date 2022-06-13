package com.soulrebel.ml.controller;

import com.soulrebel.ml.model.ApiError;
import com.soulrebel.ml.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Api(value = "stats", tags = "stats")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation Success", response = Response.class),
        @ApiResponse(code = 400, message = "400 - business error", response = ApiError.class),
        @ApiResponse(code = 500, message = "500 - server error", response = ApiError.class)
})
public interface IStatisticsController {

    @ApiOperation(value = "returns a json object with the statics number mutants and humans",
            nickname = "create-stats", response = Response.class)
    @GetMapping(value = "/stats", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    String stats();
}

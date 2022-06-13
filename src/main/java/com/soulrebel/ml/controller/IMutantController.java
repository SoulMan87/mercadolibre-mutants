package com.soulrebel.ml.controller;


import com.soulrebel.ml.model.ApiError;
import com.soulrebel.ml.model.DnaResponse;
import com.soulrebel.ml.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Api(value = "mutants", tags = "mutants")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation Success", response = Response.class),
        @ApiResponse(code = 400, message = "400 - business error", response = ApiError.class),
        @ApiResponse(code = 500, message = "500 - server error", response = ApiError.class)
})
public interface IMutantController {

    @ApiOperation(value = "receives a Json object wrapped by response and returns an" +
            " HttpStatus depending the human dna sequence checked.",
            nickname = "create-mutants", response = Response.class)
    @PostMapping(value = "/mutants",consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> mutant(@RequestBody DnaResponse dnaResponse);
}

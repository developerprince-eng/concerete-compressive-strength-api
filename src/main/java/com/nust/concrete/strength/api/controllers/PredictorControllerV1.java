package com.nust.concrete.strength.api.controllers;

import com.nust.concrete.strength.api.models.dto.ConcreteCompositionInputDto;
import com.nust.concrete.strength.api.services.PredictorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/ccst/")
@SwaggerDefinition(tags = {@Tag(name = "Concrete Strength Prediction", description = "Version 1 for Concrete Strength Prediction the Concrete Strength is measure in Mega Pascals MPa. Take note that each mixture is measured as kg in a m3.")})
@Api(tags = {"Concrete Strength Prediction"})
public class PredictorControllerV1 {

    @Autowired
    private PredictorService predictorService;

    @PostMapping("/")
    @Operation(
            summary = "Get The Strength of Concrete by Inputting Composition of Concrete",
            description = "This endpoint enables on to be able to concrete strength",
            tags = { "Vehicle Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getConcreteStrength(@RequestBody ConcreteCompositionInputDto concreteCompositionInputDto) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        return predictorService.resolveCCSST(concreteCompositionInputDto);
    }
}

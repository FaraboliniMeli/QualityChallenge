package br.com.marcello.QualityChallenge.controller;

import br.com.marcello.QualityChallenge.dto.DistrictDto;
import br.com.marcello.QualityChallenge.exceptionHandling.ApiExceptionResponse;
import br.com.marcello.QualityChallenge.repository.DistrictRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/districts")
@Tag(name = "District Controller", description = "API for District Entity")
@RestController
public class HealthCheckDistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Operation(summary = "Find a district by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District found",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DistrictDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid name provided.",
                        content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiExceptionResponse.class))
                        }),
            @ApiResponse(responseCode = "404", description = "District not found.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server error.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiExceptionResponse.class))
                    })
    })
    @GetMapping
    public DistrictDto healthCheck(@Parameter(description = "Name of district to be searched.")
            @RequestParam (value = "name") String name) {

        return this.districtRepository.findDistrictByName(name);

    }

}

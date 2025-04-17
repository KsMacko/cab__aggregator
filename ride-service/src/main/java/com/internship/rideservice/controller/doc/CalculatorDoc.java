package com.internship.rideservice.controller.doc;

import com.internship.rideservice.util.exceptions.BaseExceptionDto;
import com.internship.rideservice.util.exceptions.BaseValidationException;
import com.internship.rideservice.enums.FareType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Tag(
        name = "Operations for calculating ride prices",
        description = "Endpoints for calculating ride prices based on distance and fare type"
)
@RequestMapping("/api/v1/prices")
public interface CalculatorDoc {

    @Operation(
            summary = "Calculate ride price",
            description = "Calculates the price of a ride based on the provided distance and fare type"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Price calculated successfully",
                    content = @Content(schema = @Schema(implementation = BigDecimal.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the fare type is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @GetMapping("/calculate")
    ResponseEntity<BigDecimal> calculatePrice(
            @Parameter(description = "Distance of the ride in kilometers", example = "15.0", required = true)
            @RequestParam Float distance,
            @Parameter(description = "Type of the fare", example = "ECONOMY", required = true)
            @RequestParam FareType fareType);
}
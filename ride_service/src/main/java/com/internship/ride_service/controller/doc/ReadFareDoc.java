package com.internship.ride_service.controller.doc;

import com.internship.ride_service.dto.transfer.FarePackageDto;
import com.internship.ride_service.util.exceptions.BaseExceptionDto;
import com.internship.ride_service.util.exceptions.BaseValidationException;
import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.enums.FareType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Operations for reading fare data",
        description = "Endpoints for retrieving fare types"
)
@RequestMapping("/api/v1/fares")
public interface ReadFareDoc {

    @Operation(
            summary = "Get all fare types",
            description = "Retrieves a list of all available fare types"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fare types retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FareDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping
    FarePackageDto getAllFares();

    @Operation(
            summary = "Get a fare type by type",
            description = "Retrieves a single fare type by its unique type"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fare type retrieved successfully",
                    content = @Content(schema = @Schema(implementation = FareDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the fare type is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping("/{type}")
    FareDto getFareByType(
            @Parameter(description = "Unique type of the fare", example = "ECONOMY", required = true)
            @PathVariable FareType type);
}
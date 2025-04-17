package com.internship.ride_service.controller.doc;

import com.internship.ride_service.dto.request.RequestFareDto;
import com.internship.ride_service.util.exceptions.BaseExceptionDto;
import com.internship.ride_service.util.exceptions.BaseValidationException;
import com.internship.ride_service.dto.response.ResponseFareDto;
import com.internship.ride_service.enums.FareType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Operations for managing fare data",
        description = "Endpoints for creating, updating, and deleting fare types"
)
@RequestMapping("/api/v1/fares")
public interface CommandFareDoc {

    @Operation(
            summary = "Create a new fare type",
            description = "Creates a new fare type based on the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Fare type created successfully",
                    content = @Content(schema = @Schema(implementation = ResponseFareDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Fare type already exists",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping
    ResponseEntity<ResponseFareDto> createFare(
            @Parameter(description = "Fare data to create", required = true)
            @Valid
            @RequestBody RequestFareDto fareDto);

    @Operation(
            summary = "Delete a fare type by type",
            description = "Deletes a fare type based on its unique identifier (type)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Fare type deleted successfully"
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
    @DeleteMapping("/{type}")
    ResponseEntity<Void> deleteFare(
            @Parameter(description = "Unique type of the fare", example = "ECONOMY", required = true)
            @PathVariable FareType type);

    @Operation(
            summary = "Update an existing fare type",
            description = "Updates an existing fare type with the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fare type updated successfully",
                    content = @Content(schema = @Schema(implementation = ResponseFareDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the fare type is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PutMapping
    ResponseEntity<ResponseFareDto> updateFare(
            @Parameter(description = "Updated fare data", required = true)
            @RequestBody RequestFareDto fareDto);
}
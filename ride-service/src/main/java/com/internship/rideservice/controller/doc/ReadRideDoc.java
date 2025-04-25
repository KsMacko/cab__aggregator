package com.internship.rideservice.controller.doc;

import com.internship.rideservice.util.exceptions.BaseExceptionDto;
import com.internship.rideservice.dto.response.ResponseRideDto;
import com.internship.rideservice.dto.transfer.RideFilterRequest;
import com.internship.rideservice.dto.transfer.RidePackageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Operations for reading ride data", description = "Endpoints for retrieving ride-related information")
@RequestMapping("api/v1/rides")
public interface ReadRideDoc {

    @Operation(
            summary = "Get ride by id",
            description = "Retrieves complete ride data by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride found",
                    content = @Content(schema = @Schema(implementation = ResponseRideDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride data is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ResponseRideDto> getRideById(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String id);

    @Operation(
            summary = "Get all rides with filters",
            description = "Retrieves a paginated and sorted list of rides based on various filter criteria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rides retrieved successfully",
                    content = @Content(schema = @Schema(implementation = RidePackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })

    @GetMapping
    ResponseEntity<RidePackageDto> getAllRides(@ParameterObject @Validated RideFilterRequest filterRequest);
}
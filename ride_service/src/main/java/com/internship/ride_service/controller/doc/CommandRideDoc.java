package com.internship.ride_service.controller.doc;

import com.internship.ride_service.dto.request.RequestRideDto;
import com.internship.ride_service.util.exceptions.BaseExceptionDto;
import com.internship.ride_service.util.exceptions.BaseValidationException;
import com.internship.ride_service.dto.response.ResponseRideDto;
import com.internship.ride_service.enums.RideStatus;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Tag(
        name = "Operations for managing ride data",
        description = "Endpoints for creating, updating, and deleting ride-related information"
)
@RequestMapping("/api/v1/rides")
public interface CommandRideDoc {

    @Operation(
            summary = "Create a new ride",
            description = "Creates a new ride based on the provided ride data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ride created successfully",
                    content = @Content(schema = @Schema(implementation = ResponseRideDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PostMapping
    ResponseEntity<ResponseRideDto> createRide(
            @Parameter(description = "Ride data to create", required = true)
            @Valid
            @RequestBody RequestRideDto rideDto);

    @Operation(
            summary = "Delete a ride by ID",
            description = "Deletes a ride based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ride deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @DeleteMapping
    ResponseEntity<Void> deleteRide(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @RequestParam String id);

    @Operation(
            summary = "Change ride status to ACCEPTED",
            description = "Changes the ride status to ACCEPTED and assigns a driver"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride status changed successfully",
                    content = @Content(schema = @Schema(implementation = RideStatus.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping("/{rideId}/status/accepted")
    ResponseRideDto changeRideStatusToAccepted(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String rideId,
            @Parameter(description = "Driver data to assign", required = true)
            @RequestParam Long driverId);

    @Operation(
            summary = "Change ride status to WAIT_FOR_PASSENGER",
            description = "Changes the ride status to WAIT_FOR_PASSENGER and sets start waiting time"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride status changed successfully",
                    content = @Content(schema = @Schema(implementation = RideStatus.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping("/{rideId}/status/wait-for-passenger")
    ResponseRideDto changeRideStatusToWaitingForPassenger(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String rideId);

    @Operation(
            summary = "Change ride status to IN_PROGRESS",
            description = "Changes the ride status to IN_PROGRESS and sets start time"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride status changed successfully",
                    content = @Content(schema = @Schema(implementation = RideStatus.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping("/{rideId}/status/in-progress")
    ResponseRideDto changeRideStatusToInProgress(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String rideId);

    @Operation(
            summary = "Change ride status to RECALCULATED",
            description = "Changes the ride status to RECALCULATED, sets end time, and recalculates price"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride status changed successfully and price recalculated",
                    content = @Content(schema = @Schema(implementation = BigDecimal.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping("/{rideId}/status/recalculated")
    ResponseRideDto changeRideStatusRecalculated(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String rideId);

    @Operation(
            summary = "Change ride status to COMPLETED",
            description = "Changes the ride status to COMPLETED"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride status changed successfully",
                    content = @Content(schema = @Schema(implementation = RideStatus.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PostMapping("/{rideId}/status/completed")
    ResponseRideDto changeRideStatusToCompleted(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String rideId);
}
package com.internship.driverservice.controller.doc;

import com.internship.driverservice.dto.request.RequestProfileDto;
import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.utils.exceptions.transfer.BaseException;
import com.internship.driverservice.utils.exceptions.transfer.BaseValidationException;
import com.internship.driverservice.utils.validation.ValidationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Operations for managing driver profiles",
        description = "Endpoints for creating, updating, and deleting driver profiles"
)
public interface ProfileCommandDoc {

    @Operation(
            summary = "Create a new driver profile",
            description = "Creates a new driver profile based on the provided profile data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Driver profile created successfully",
                    content = @Content(schema = @Schema(implementation = ResponseProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PostMapping
    ResponseEntity<ResponseProfileDto> createProfile(
            @Parameter(description = "Driver profile data to create", required = true)
            @Valid
            @RequestBody
            RequestProfileDto profileDto);

    @Operation(
            summary = "Update an existing driver profile",
            description = "Updates an existing driver profile with the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver profile updated successfully",
                    content = @Content(schema = @Schema(implementation = ResponseProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PutMapping("/{id}")
    ResponseProfileDto updateProfile(
            @Parameter(description = "Updated driver profile data", required = true)
            @PathVariable Long id,
            @RequestBody RequestProfileDto profileDto);

    @Operation(
            summary = "Delete a driver profile by ID",
            description = "Deletes a driver profile based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Driver profile deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProfile(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            @PathVariable Long id);

    @Operation(
            summary = "Set a rate for a driver",
            description = "Sets a new rate for a driver based on the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Rate set successfully",
                    content = @Content(schema = @Schema(implementation = ResponseRateDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver or rate is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @PostMapping("/rates")
    ResponseRateDto setRateToDriver(
            @Parameter(description = "Rate data to set for the driver", required = true)
            @Valid
            @RequestBody RequestRateDto rateDto);

    @Operation(
            summary = "Delete a rate from a driver",
            description = "Deletes a specific rate associated with a driver"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Rate deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the rate is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @DeleteMapping("/rates/{id}")
    ResponseEntity<Void> deleteRateFromDriver(
            @Parameter(description = "Unique identifier of the rate", example = "1", required = true)
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            @PathVariable Long id);
}
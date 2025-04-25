package com.internship.driverservice.controller.doc;

import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.transfer.DriverFilterRequest;
import com.internship.driverservice.dto.transfer.ProfilePackageDto;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = "Operations for retrieving driver profile data",
        description = "Endpoints for reading driver profile information"
)
public interface ProfileReadDoc {

    @Operation(
            summary = "Find all driver profiles",
            description = "Retrieves a paginated list of driver profiles based on filters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of driver profiles retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProfilePackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping
    ResponseEntity<ProfilePackageDto> findAllDrivers(
            @ModelAttribute
            @Parameter(description = "Filter options for driver profiles", required = true)
            @Validated
            DriverFilterRequest filter);

    @Operation(
            summary = "Find a driver profile by ID",
            description = "Retrieves a driver profile based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ResponseProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ResponseProfileDto> findById(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            @PathVariable Long id);
}
package com.internship.passenger_service.controller.doc;

import com.internship.passenger_service.utils.exceptions.BaseException;
import com.internship.passenger_service.utils.exceptions.BaseValidationException;
import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.dto.transfer.DataPackageDto;
import com.internship.passenger_service.dto.transfer.ProfileFilterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = "Operations for reading passenger data",
        description = "Endpoints for retrieving profile-related information")
public interface ReadDoc {
    @Operation(
            summary = "Get passenger profile by id",
            description = "Retrieves complete profile data by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile found",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Occurs because of the incorrect input of id",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the passenger is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}")
    ProfileDto readPassengerById(
            @Parameter(description = "Unique identifier of the passenger", example = "1", required = true)
            @PathVariable Long id);
    @Operation(
            summary = "Get all profiles with filters",
            description = "Retrieves a paginated and sorted list of passenger profiles based on various filter criteria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rides retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DataPackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })

    @GetMapping
    DataPackageDto readAllProfiles(@ParameterObject ProfileFilterRequest filterRequest);
}

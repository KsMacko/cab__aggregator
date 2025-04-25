package com.internship.passengerservice.controller.doc;

import com.internship.passengerservice.utils.exceptions.BaseException;
import com.internship.passengerservice.utils.exceptions.BaseValidationException;
import com.internship.passengerservice.dto.response.ResponseProfileDto;
import com.internship.passengerservice.dto.transfer.DataPackageDto;
import com.internship.passengerservice.dto.transfer.ProfileFilterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static com.internship.passengerservice.utils.ValidationConstants.MAX_ID_VALUE;

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
                    content = @Content(schema = @Schema(implementation = ResponseProfileDto.class))
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
    ResponseEntity<ResponseProfileDto> readPassengerById(
            @Parameter(description = "Unique identifier of the passenger", example = "1", required = true)
            @Positive(message = "id.positive")
            @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
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
    ResponseEntity<DataPackageDto> readAllProfiles(
            @Validated
            @ModelAttribute
            ProfileFilterRequest filterRequest);
}

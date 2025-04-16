package com.internship.passenger_service.controller.doc;

import com.internship.passenger_service.utils.exceptions.BaseException;
import com.internship.passenger_service.utils.exceptions.BaseValidationException;
import com.internship.passenger_service.dto.ProfileDto;
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

@Tag(
        name = "Operations with passenger data",
        description = "Endpoints for creating, updating, deleting passenger data")
public interface CommandDoc {
    @Operation(
            summary = "Creates passenger profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Profile created",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Occurs because the entity to create must not have id",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @PostMapping
    ResponseEntity<ProfileDto> createPassenger(
            @Parameter(description = "Instance of passenger's profile", required = true)
            @Valid
            @RequestBody ProfileDto profileDto);

    @Operation(
            summary = "Updates passenger's profile info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile successfully updated",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters. Occurs when the transmitted entity do not have id",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the entity to update was not found by id",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PutMapping
    ProfileDto updatePassenger(ProfileDto profileDto);

    @Operation(
            summary = "Delete passenger",
            description = "Deletes passenger by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Passenger deleted successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input of id",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger with transmitted id is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @DeleteMapping("/{id}")
    void deletePassenger(
            @Parameter(description = "Unique identifier of the passenger", example = "1", required = true)
            @PathVariable Long id);
}

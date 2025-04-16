package com.internship.ride_service.controller.doc;

import com.internship.ride_service.util.exceptions.BaseExceptionDto;
import com.internship.ride_service.util.exceptions.BaseValidationException;
import com.internship.ride_service.dto.PromoCodeDto;
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
        name = "Operations for managing promo codes",
        description = "Endpoints for creating, updating, and deleting promo codes"
)
@RequestMapping("/api/v1/promo-codes")
public interface CommandPromoDoc {

    @Operation(
            summary = "Create a new promo code",
            description = "Creates a new promo code based on the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Promo code created successfully",
                    content = @Content(schema = @Schema(implementation = PromoCodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PostMapping
    ResponseEntity<PromoCodeDto> createPromoCode(
            @Parameter(description = "Promo code data to create", required = true)
            @Valid
            @RequestBody PromoCodeDto promoCodeDto);

    @Operation(
            summary = "Delete a promo code by code",
            description = "Deletes a promo code based on its unique identifier (code)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Promo code deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the promo code is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePromoCode(
            @Parameter(description = "Unique id of the promo code")
            @PathVariable String id);

    @Operation(
            summary = "Update an existing promo code",
            description = "Updates an existing promo code with the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Promo code updated successfully",
                    content = @Content(schema = @Schema(implementation = PromoCodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the promo code is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PutMapping
    PromoCodeDto updatePromoCode(
            @Parameter(description = "Updated promo code data", required = true)
            @RequestBody PromoCodeDto promoCodeDto);
}
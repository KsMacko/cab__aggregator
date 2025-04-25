package com.internship.rideservice.controller.doc;

import com.internship.rideservice.dto.transfer.PromoCodeFilterRequest;
import com.internship.rideservice.util.exceptions.BaseExceptionDto;
import com.internship.rideservice.util.exceptions.BaseValidationException;
import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.dto.transfer.PromoCodePackageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Operations for reading promo codes",
        description = "Endpoints for retrieving promo code data"
)
@RequestMapping("/api/v1/promo-codes")
public interface ReadPromoDoc {

    @Operation(
            summary = "Get filtered promo codes",
            description = "Retrieves a paginated list of promo codes based on filters, sorting, and pagination"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Promo codes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PromoCodePackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping
    ResponseEntity<PromoCodePackageDto> getFilteredPromoCodes(@ModelAttribute @Validated PromoCodeFilterRequest filterRequest);

    @Operation(
            summary = "Get a promo code by code",
            description = "Retrieves a single promo code by its unique code"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Promo code retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ResponsePromoCodeDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the promo code is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping("/{code}/current")
    ResponseEntity<ResponsePromoCodeDto> getPromoCode(
            @Parameter(description = "Unique code of the promo code", example = "SUMMER20", required = true)
            @PathVariable String code);
}
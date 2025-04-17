package com.internship.driver_service.controller.doc;

import com.internship.driver_service.dto.response.ResponseCarDto;
import com.internship.driver_service.dto.transfer.CarFilterRequest;
import com.internship.driver_service.dto.transfer.CarPackageDto;
import com.internship.driver_service.utils.exceptions.transfer.BaseException;
import com.internship.driver_service.utils.exceptions.transfer.BaseValidationException;
import com.internship.driver_service.utils.validation.ValidationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = "Operations for retrieving car data",
        description = "Endpoints for reading car information"
)
public interface CarReadDoc {
    @Operation(
            summary = "Get the current car of a driver",
            description = "Retrieves the current car associated with a specific driver profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Current car retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ResponseCarDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver or current car is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/cars/current")
    ResponseCarDto getCurrentCar(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            @PathVariable Long id);

    @Operation(
            summary = "Find all cars based on filters",
            description = "Retrieves a paginated list of cars based on filter criteria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of cars retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CarPackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping("/cars")
    CarPackageDto getCars(
            @ModelAttribute
            @Parameter(description = "Filter options for cars", required = true)
            CarFilterRequest filter);
}

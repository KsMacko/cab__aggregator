package com.internship.driver_service.controller.doc;


import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.transfer.CarFilterRequest;
import com.internship.driver_service.dto.transfer.CarPackageDto;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.dto.transfer.ProfilePackageDto;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Operations for retrieving driver profile data",
        description = "Endpoints for reading driver profile information"
)
@RequestMapping("/api/v1/drivers")
public interface ReadDoc {

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
    ProfilePackageDto findAllDrivers(
            @ModelAttribute
            @Parameter(description = "Filter options for driver profiles", required = true)
            DriverFilterRequest filter);

    @Operation(
            summary = "Find a driver profile by ID",
            description = "Retrieves a driver profile based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}")
    ProfileDto findById(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            @PathVariable Long id);


    @Operation(
            summary = "Get the current car of a driver",
            description = "Retrieves the current car associated with a specific driver profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Current car retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CarDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver or current car is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/cars/current")
    CarDto getCurrentCar(
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
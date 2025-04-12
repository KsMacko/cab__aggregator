package com.internship.driver_service.service.query;

import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.dto.mapper.CarMapper;
import com.internship.driver_service.dto.transfer.CarFilterRequest;
import com.internship.driver_service.dto.transfer.CarPackageDto;
import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.CarRepo;
import com.internship.driver_service.service.specification.CarSpecification;
import com.internship.driver_service.utils.validation.CarValidationManager;
import com.internship.driver_service.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.internship.driver_service.utils.validation.ValidationConstants.DEFAULT_PAGE_SIZE;
import static com.internship.driver_service.utils.validation.ValidationConstants.DEFAULT_PAGE_VALUE;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ReadCarService {
    private final CarRepo carRepo;
    private final CarSpecification carSpecification;
    private final ProfileValidationManager profileValidationManager;
    private final CarValidationManager carValidationManager;

    @Transactional(readOnly = true)
    public CarPackageDto readAllCars(CarFilterRequest filter) {
        if (isNull(filter.getPage()))
            filter.setPage(DEFAULT_PAGE_VALUE);
        if (isNull(filter.getPageSize()))
            filter.setPageSize(DEFAULT_PAGE_SIZE);
        Pageable pageable = createPageableObject(filter);
        Specification<Car> spec = carSpecification.createFilterSpecification(filter);

        Page<Car> resultPage = carRepo.findAll(spec, pageable);
        return createCarPackageDto(resultPage);
    }

    @Transactional(readOnly = true)
    public CarDto getCurrentCarByProfileId(Long profileId) {
        profileValidationManager.checkIfProfileIdNotNull(profileId);
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(profileId);
        Car car = carRepo.findByDriverProfileAndIsCurrent(driverProfile, true);
        carValidationManager.checkCarNotNull(car);
        return CarMapper.converter.handleEntity(car);
    }

    private CarPackageDto createCarPackageDto(Page<Car> resultPage) {
        List<CarDto> carsDto = resultPage.getContent().stream()
                .map(CarMapper.converter::handleEntity)
                .toList();

        return CarPackageDto.builder()
                .carsDto(carsDto)
                .totalElements(resultPage.getTotalElements())
                .pageNumber(resultPage.getNumber())
                .pageSize(resultPage.getSize())
                .totalPages(resultPage.getTotalPages())
                .build();
    }

    private Pageable createPageableObject(CarFilterRequest filter) {
        return PageRequest.of(filter.getPage(), filter.getPageSize());
    }
}

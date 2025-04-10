package com.internship.driver_service.service;

import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.Projection;
import com.internship.driver_service.dto.mapper.CarMapper;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.transfer.DataPackageDto;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.CarRepo;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.service.specification.DriverSpecificationService;
import com.internship.driver_service.utils.CarValidationManager;
import com.internship.driver_service.utils.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final RateRepo rateRepo;
    private final CarRepo carRepo;
    private final DriverSpecificationService specificationService;
    private final ProfileValidationManager profileValidationManager;
    private final CarValidationManager carValidationManager;

    @Transactional(readOnly = true)
    public DataPackageDto readAllDrivers(DriverFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        return createDataPackageDto(driverProfileRepo.findAllDrivers(spec, pageable));
    }

    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        profileValidationManager.checkIfProfileExists(id);
        DriverProfile driverProfile = driverProfileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("driver.notFound"));
        Byte rate = rateRepo.findDriverRatingByProfileId(id);
        return ProfileMapper.converter.handleEntity(driverProfile, rate);

    }

    @Transactional(readOnly = true)
    public CarDto getCurrentCarByProfileId(Long profileId) {
        profileValidationManager.checkIfProfileIdNotNull(profileId);
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(profileId);
        Car car = carRepo.findByDriverProfileAndCurrent(driverProfile, true);
        carValidationManager.checkCarNotNull(car);
        return CarMapper.converter.handleEntity(car);
    }

    @Transactional(readOnly = true)
    public DataPackageDto createDataPackageDto(Page<Projection> resultPage) {
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(projection -> ProfileDto.builder()
                        .profileId(projection.getProfileId())
                        .phone(projection.getPhone())
                        .firstName(projection.getFirstName())
                        .lastName(projection.getLastName())
                        .driverStatus(projection.getDriverStatus())
                        .fareType(projection.getFareType())
                        .rate(projection.getRate())
                        .build())
                .toList();
        return DataPackageDto.builder()
                .profilesDto(profiles)
                .totalElements(resultPage.getTotalElements())
                .pageNumber(resultPage.getNumber())
                .totalPages(resultPage.getTotalPages())
                .pageSize(resultPage.getSize())
                .build();
    }

    private Pageable createPageableObject(DriverFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                        filterRequest.order().toString()),
                filterRequest.sortBy().getFieldName());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }

}

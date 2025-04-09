package com.internship.driver_service.service;

import com.internship.driver_service.dto.Projection;
import com.internship.driver_service.dto.transfer_objects.DataPackageDto;
import com.internship.driver_service.dto.transfer_objects.DriverFilterRequest;
import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.WrappedResponse;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.service.specification.DriverSpecificationService;
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
    private final DriverSpecificationService specificationService;
    private final ProfileValidationManager validationManager;

    @Transactional(readOnly = true)
    public DataPackageDto readAllDrivers(DriverFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        return createDataPackageDto(driverProfileRepo.findAllDrivers(spec, pageable));
    }
    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        validationManager.checkIfUserExists(id);
        DriverProfile driverProfile =  driverProfileRepo.findById(id)
                .orElseThrow(()->new RuntimeException("driver.notFound"));
        Byte rate = rateRepo.findDriverRatingByProfileId(id);
        return ProfileMapper.converter.handleEntity(driverProfile, rate);

    }
    @Transactional(readOnly = true)
    public WrappedResponse<FareType> readDriverProfileFareTypeById(Long driverId) {
        validationManager.checkIfUserExists(driverId);
        return new WrappedResponse<>(driverProfileRepo.getFareTypeByProfileId(driverId));
    }
    @Transactional(readOnly = true)
    public WrappedResponse<DriverStatus> readDriverProfileStatusById(Long driverId) {
        validationManager.checkIfUserExists(driverId);
        return new WrappedResponse<>(driverProfileRepo.getDriverStatusByProfileId(driverId));
    }
    @Transactional(readOnly = true)
    public WrappedResponse<Byte> readDriverProfileRatingById(Long driverId) {
        validationManager.checkIfUserExists(driverId);
        Byte rate = rateRepo.findDriverRatingByProfileId(driverId);
        if(rate ==null) throw new RuntimeException("driver.profile.rating.notFound");
        return new WrappedResponse<>(rate);
    }
    public DataPackageDto createDataPackageDto(Page<Projection> resultPage) {
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(projection -> ProfileDto.builder()
                        .profileId(projection.getProfileId())
                        .phone(projection.getPhone())
                        .firstName(projection.getFirstName())
                        .lastName(projection.getLastName())
                        .driverStatus(projection.getDriverStatus())
                        .carNumber(projection.getCarNumber())
                        .carDescription(projection.getCarDescription())
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

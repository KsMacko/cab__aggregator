package com.internship.driver_service.service.query;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.Projection;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.dto.transfer.ProfilePackageDto;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.service.specification.DriverSpecificationService;
import com.internship.driver_service.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.internship.driver_service.utils.validation.ValidationConstants.DEFAULT_PAGE_SIZE;
import static com.internship.driver_service.utils.validation.ValidationConstants.DEFAULT_PAGE_VALUE;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ReadDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final RateRepo rateRepo;
    private final DriverSpecificationService specificationService;
    private final ProfileValidationManager profileValidationManager;

    @Transactional(readOnly = true)
    public ProfilePackageDto readAllDrivers(DriverFilterRequest filter) {
        if (isNull(filter.getPage()))
            filter.setPage(DEFAULT_PAGE_VALUE);
        if (isNull(filter.getSize()))
            filter.setSize(DEFAULT_PAGE_SIZE);
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        return createDataPackageDto(driverProfileRepo.findAllDrivers(spec, pageable));
    }

    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        profileValidationManager.checkIfProfileExists(id);
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(id);
        Byte rate = rateRepo.findDriverRatingByProfileId(id);
        return ProfileMapper.converter.handleEntity(driverProfile, rate);

    }

    @Transactional(readOnly = true)
    public ProfilePackageDto createDataPackageDto(Page<Projection> resultPage) {
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(projection -> ProfileDto.builder()
                        .profileId(projection.getProfileId())
                        .phone(projection.getPhone())
                        .firstName(projection.getFirstName())
                        .lastName(projection.getLastName())
                        .driverStatus(projection.getDriverStatus().toString())
                        .fareType(projection.getFareType().toString())
                        .rate(projection.getRate())
                        .build())
                .toList();
        return ProfilePackageDto.builder()
                .profilesDto(profiles)
                .totalElements(resultPage.getTotalElements())
                .pageNumber(resultPage.getNumber())
                .totalPages(resultPage.getTotalPages())
                .pageSize(resultPage.getSize())
                .build();
    }

    private Pageable createPageableObject(DriverFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                        filterRequest.getOrder()),
                filterRequest.getSortBy());
        return PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);
    }

}

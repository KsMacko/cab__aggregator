package com.internship.driver_service.service.query;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.dto.transfer.ProfilePackageDto;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.enums.FieldFilter;
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
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        Page<DriverProfile> resultPage = driverProfileRepo.findAll(spec, pageable);
        return createDataPackageDto(resultPage);
    }

    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        profileValidationManager.checkIfProfileExists(id);
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(id);
        Integer rate = rateRepo.findDriverRatingByProfileId(id);
        return ProfileMapper.converter.handleEntity(driverProfile, rate);

    }

    @Transactional(readOnly = true)
    public ProfilePackageDto createDataPackageDto(Page<DriverProfile> resultPage) {
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(ProfileMapper.converter::handleEntity)
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
        if(isNull(filterRequest.getSortBy())) filterRequest.setSortBy(FieldFilter.FIRST_NAME.getFieldName());

        Sort sort = Sort.by(Sort.Direction.fromString(
                        filterRequest.getOrder()),
                FieldFilter.valueOf(filterRequest.getSortBy()).getFieldName());
        return PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);
    }

}

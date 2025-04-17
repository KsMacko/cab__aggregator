package com.internship.driverservice.service.query;

import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.mapper.ProfileMapper;
import com.internship.driverservice.dto.transfer.DriverFilterRequest;
import com.internship.driverservice.dto.transfer.ProfilePackageDto;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.enums.FieldFilter;
import com.internship.driverservice.repo.DriverProfileRepo;
import com.internship.driverservice.repo.RateRepo;
import com.internship.driverservice.service.specification.DriverSpecificationService;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
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
    private final ProfileMapper profileMapper;
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
    public ResponseProfileDto readDriverProfileById(Long id) {
        profileValidationManager.checkIfProfileExists(id);
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(id);
        Integer rate = rateRepo.findDriverRatingByProfileId(id);
        return profileMapper.handleEntity(driverProfile, rate);

    }

    @Transactional(readOnly = true)
    public ProfilePackageDto createDataPackageDto(Page<DriverProfile> resultPage) {
        List<ResponseProfileDto> profiles = resultPage.getContent().stream()
                .map(profileMapper::handleEntity)
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
        if (isNull(filterRequest.getSortBy())) filterRequest.setSortBy(FieldFilter.FIRST_NAME.getFieldName());

        Sort sort = Sort.by(Sort.Direction.fromString(
                        filterRequest.getOrder()),
                FieldFilter.valueOf(filterRequest.getSortBy()).getFieldName());
        return PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);
    }

}

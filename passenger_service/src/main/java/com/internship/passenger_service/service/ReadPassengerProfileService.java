package com.internship.passenger_service.service;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.dto.RateDto;
import com.internship.passenger_service.dto.mapper.ProfileMapper;
import com.internship.passenger_service.dto.transfer.DataPackageDto;
import com.internship.passenger_service.dto.transfer.ProfileFilterRequest;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.enums.FieldsToSort;
import com.internship.passenger_service.repo.PassengerProfileRepo;
import com.internship.passenger_service.repo.RateRepo;
import com.internship.passenger_service.service.specification.PassengerProfileSpecification;
import com.internship.passenger_service.utils.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.internship.passenger_service.utils.ValidationConstants.DEFAULT_PAGE_SIZE;
import static com.internship.passenger_service.utils.ValidationConstants.DEFAULT_PAGE_VALUE;

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerProfileSpecification passengerProfileSpecification;
    private final ProfileValidationManager validationManager;
    private final ProfileMapper profileMapper;

    @Transactional(readOnly = true)
    public ProfileDto readPassengerProfile(Long id) {
        PassengerProfile passengerProfile = validationManager.getProfileByIdIfExists(id);
        return profileMapper.handleEntity(passengerProfile);
    }

    @Transactional(readOnly = true)
    public DataPackageDto readPassengerProfiles(ProfileFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<PassengerProfile> spec = passengerProfileSpecification.filterBy(filter);
        Page<PassengerProfile> resultPage = passengerProfileRepo.findAll(spec, pageable);
        return convertToDataPackageDto(resultPage);
    }
    public Pageable createPageableObject(ProfileFilterRequest filter) {
        int page = Optional.ofNullable(filter.getPage()).orElse(DEFAULT_PAGE_VALUE);
        int size = Optional.ofNullable(filter.getSize()).orElse(DEFAULT_PAGE_SIZE);
        Sort sort = Sort.by(Sort.Direction.fromString(
                filter.getOrder()),
                FieldsToSort.valueOf(filter.getSortBy()).getFieldName());
        return PageRequest.of(page, size, sort);
    }

    public DataPackageDto convertToDataPackageDto(Page<PassengerProfile> resultPage) {
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(profileMapper::handleEntity)
                .toList();
        return DataPackageDto.builder()
                .profiles(profiles)
                .totalElements(resultPage.getTotalElements())
                .pageNumber(resultPage.getNumber())
                .totalPages(resultPage.getTotalPages())
                .pageSize(resultPage.getSize())
                .build();
    }
}

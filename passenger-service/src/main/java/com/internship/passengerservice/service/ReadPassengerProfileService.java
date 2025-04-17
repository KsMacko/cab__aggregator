package com.internship.passengerservice.service;

import com.internship.passengerservice.dto.response.ResponseProfileDto;
import com.internship.passengerservice.dto.mapper.ProfileMapper;
import com.internship.passengerservice.dto.transfer.DataPackageDto;
import com.internship.passengerservice.dto.transfer.ProfileFilterRequest;
import com.internship.passengerservice.entity.PassengerProfile;
import com.internship.passengerservice.enums.FieldsToSort;
import com.internship.passengerservice.repo.PassengerProfileRepo;
import com.internship.passengerservice.service.specification.PassengerProfileSpecification;
import com.internship.passengerservice.utils.ProfileValidationManager;
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

import static com.internship.passengerservice.utils.ValidationConstants.DEFAULT_PAGE_SIZE;
import static com.internship.passengerservice.utils.ValidationConstants.DEFAULT_PAGE_VALUE;

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerProfileSpecification passengerProfileSpecification;
    private final ProfileValidationManager validationManager;
    private final ProfileMapper profileMapper;

    @Transactional(readOnly = true)
    public ResponseProfileDto readPassengerProfile(Long id) {
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
        List<ResponseProfileDto> profiles = resultPage.getContent().stream()
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

package com.internship.passenger_service.service;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.dto.Projection;
import com.internship.passenger_service.dto.mapper.ProfileMapper;
import com.internship.passenger_service.dto.transfer_objects.DataPackageDto;
import com.internship.passenger_service.dto.transfer_objects.ProfileFilterRequest;
import com.internship.passenger_service.entity.PassengerProfile;
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

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerProfileSpecification passengerProfileSpecification;
    private final RateRepo rateRepo;
    private final ProfileValidationManager validationManager;

    @Transactional(readOnly= true)
    public ProfileDto readPassengerProfile(Long id) {
        validationManager.checkIfIdNotNull(id);
        PassengerProfile passengerProfile =  validationManager.getProfileByIdIfExists(id);
        Byte rate = rateRepo.findPassengerRatingByProfileId(id);
        return ProfileMapper.converter.handleEntity(passengerProfile, rate);
    }

    @Transactional(readOnly= true)
    public DataPackageDto readPassengerProfiles(ProfileFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<PassengerProfile> spec = passengerProfileSpecification.filterBy(filter);
        Page<Projection> resultPage = passengerProfileRepo.findAllPassengers(spec, pageable);
        return convertToDataPackageDto(resultPage);
    }

    public Pageable createPageableObject(ProfileFilterRequest filter) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                filter.order().toString()),
                filter.sortBy().toString());
        return PageRequest.of(filter.page(), filter.size(), sort);
    }
    public DataPackageDto convertToDataPackageDto(Page<Projection> resultPage){
        List<ProfileDto> profiles = resultPage.getContent().stream()
                .map(projection -> ProfileDto.builder()
                        .profileId(projection.getProfileId())
                        .phone(projection.getPhone())
                        .firstName(projection.getFirstName())
                        .email(projection.getEmail())
                        .rate(projection.getRate())
                        .build())
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

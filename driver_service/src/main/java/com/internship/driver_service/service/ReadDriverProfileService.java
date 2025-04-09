package com.internship.driver_service.service;

import com.internship.driver_service.config.ValidationConstants;
import com.internship.driver_service.dto.DataPackageDto;
import com.internship.driver_service.dto.DriverFilterRequest;
import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.RatingPerProfile;
import com.internship.driver_service.dto.WrappedResponse;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.service.specification.DriverSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final RateRepo rateRepo;
    private final DriverSpecificationService specificationService;

    @Transactional(readOnly = true)
    public DataPackageDto readAllDrivers(DriverFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findAll(spec, pageable);
        List<Long> profileIds = driverProfilePage.getContent()
                .stream()
                .map(DriverProfile::getProfileId)
                .toList();
        Map<Long, Byte> ratingsMap = rateRepo.findRatingsByProfileIds(profileIds,
                        ValidationConstants.LAST_AMOUNT_OF_RATES)
                .stream()
                .collect(Collectors.toMap(
                        RatingPerProfile::profileId,
                        RatingPerProfile::rating
                ));
        List<ProfileDto> driverProfilesDto = driverProfilePage.getContent()
                .stream()
                .map(driverProfile -> mapDriverProfileToDto(driverProfile, ratingsMap))
                .toList();
        return createDataPackageDto(driverProfilePage, driverProfilesDto);
    }
    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        return mapDriverProfileToDto (
                driverProfileRepo.findById(id).orElseThrow(
                        ()->new RuntimeException("driver.notFound")));
    }
    @Transactional(readOnly = true)
    public WrappedResponse<FareType> readDriverProfileFareTypeById(Long driverId) {
        validateTheId(driverId);
        return new WrappedResponse<>(driverProfileRepo.getFareTypeByProfileId(driverId));
    }
    @Transactional(readOnly = true)
    public WrappedResponse<DriverStatus> readDriverProfileStatusById(Long driverId) {
        validateTheId(driverId);
        return new WrappedResponse<>(driverProfileRepo.getDriverStatusByProfileId(driverId));
    }
    @Transactional(readOnly = true)
    public WrappedResponse<Byte> readDriverProfileRatingById(Long driverId) {
        validateTheId(driverId);
        Byte rate = rateRepo.findDriverRatingByProfileId(
                driverId,
                ValidationConstants.LAST_AMOUNT_OF_RATES);
        if(rate ==null) throw new RuntimeException("driver.profile.rating.notFound");
        return new WrappedResponse<>(rate);
    }
    public DataPackageDto createDataPackageDto(Page<DriverProfile> driverProfilePage,
                                               List<ProfileDto> driverProfilesDto) {
        return DataPackageDto.builder()
                .profilesDto(driverProfilesDto)
                .pageNumber(driverProfilePage.getNumber())
                .pageSize(driverProfilePage.getSize())
                .totalElements(driverProfilePage.getTotalElements())
                .totalPages(driverProfilePage.getTotalPages())
                .build();
    }
    private Pageable createPageableObject(DriverFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                filterRequest.order().toString()),
                filterRequest.sortBy().getFieldName());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }
    private void validateTheId(Long id){
        if(driverProfileRepo.findById(id).isEmpty())
            throw new RuntimeException("driver.notFound");
    }
    private ProfileDto mapDriverProfileToDto(DriverProfile driverProfile) {
        Byte rating = rateRepo.findDriverRatingByProfileId(driverProfile.getProfileId(),
                ValidationConstants.LAST_AMOUNT_OF_RATES);
        return ProfileMapper.converter.handleEntity(driverProfile, rating);
    }
    private ProfileDto mapDriverProfileToDto(DriverProfile driverProfile, Map<Long, Byte> ratingsMap) {
        Byte rating = ratingsMap.get(driverProfile.getProfileId());
        return ProfileMapper.converter.handleEntity(driverProfile, rating);
    }

}

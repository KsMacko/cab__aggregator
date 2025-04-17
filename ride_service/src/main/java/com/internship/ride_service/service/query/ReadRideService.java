package com.internship.ride_service.service.query;

import com.internship.ride_service.dto.response.ResponseRideDto;
import com.internship.ride_service.dto.mapper.RideMapper;
import com.internship.ride_service.dto.transfer.RideFilterRequest;
import com.internship.ride_service.dto.transfer.RidePackageDto;
import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.RideFieldsToFilter;
import com.internship.ride_service.enums.RideStatus;
import com.internship.ride_service.util.validators.RideValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.internship.ride_service.entity.Ride.Fields.createdAt;
import static com.internship.ride_service.entity.Ride.Fields.driverId;
import static com.internship.ride_service.entity.Ride.Fields.fareType;
import static com.internship.ride_service.entity.Ride.Fields.passengerId;
import static com.internship.ride_service.entity.Ride.Fields.status;
import static java.util.Objects.nonNull;


@Service
@RequiredArgsConstructor
public class ReadRideService {

    private final MongoTemplate mongoTemplate;
    private final RideMapper rideMapper;
    private final RideValidationManager rideValidationManager;

    @Transactional(readOnly = true)
    public RidePackageDto getAllRides(RideFilterRequest filterRequest) {
        Query query = buildQuery(filterRequest);
        query.with(createPageableObject(filterRequest));

        List<ResponseRideDto> rides = mongoTemplate.find(query, Ride.class)
                .stream()
                .map(rideMapper::handleEntity)
                .toList();
        long totalElements = mongoTemplate.count(query, Ride.class);

        return createRidePackage(rides, totalElements, filterRequest);
    }

    @Transactional(readOnly = true)
    public ResponseRideDto getRideById(String id) {
        Ride ride = rideValidationManager.getRideByIdIfExists(id);
        return rideMapper.handleEntity(ride);
    }

    private Query buildQuery(RideFilterRequest filterRequest) {
        Criteria criteria = new Criteria();
        addCriteriaIfNotNull(criteria, createdAt, filterRequest.getCreatedDate());
        addCriteriaIfNotNull(criteria, driverId, filterRequest.getDriverId());
        addCriteriaIfNotNull(criteria, passengerId, filterRequest.getPassengerId());
        addCriteriaIfNotNull(criteria, status, RideStatus.valueOf(filterRequest.getStatus()));
        addCriteriaIfNotNull(criteria, fareType, FareType.valueOf(filterRequest.getFareType()));
        if (filterRequest.getSortBy() != null) {
            criteria.and(RideFieldsToFilter.valueOf(filterRequest.getSortBy()).getFieldName()).ne(null);
        }
        return new Query(criteria);
    }

    private void addCriteriaIfNotNull(Criteria criteria, String fieldName, Object value) {
        if (nonNull(value)) {
            criteria.and(fieldName).is(value);
        }
    }

    private Pageable createPageableObject(RideFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                filterRequest.getOrder()),
                RideFieldsToFilter.valueOf(filterRequest.getSortBy()).getFieldName());
        return PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);
    }

    public RidePackageDto createRidePackage(List<ResponseRideDto> rides, long totalElements, RideFilterRequest filterRequest) {
        return new RidePackageDto(
                rides,
                totalElements,
                filterRequest.getPage(),
                rides.size(),
                (int) Math.ceil((double) totalElements / filterRequest.getSize())
        );
    }
}
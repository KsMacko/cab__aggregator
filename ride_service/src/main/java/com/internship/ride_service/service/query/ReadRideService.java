package com.internship.ride_service.service.query;

import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.dto.mapper.RideMapper;
import com.internship.ride_service.dto.transfer.RideFilterRequest;
import com.internship.ride_service.dto.transfer.RidePackageDto;
import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.util.RideValidationManager;
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

        List<RideDto> rides = mongoTemplate.find(query, Ride.class)
                .stream()
                .map(rideMapper::handleEntity)
                .toList();
        long totalElements = mongoTemplate.count(query, Ride.class);

        return createRidePackage(rides, totalElements, filterRequest);
    }

    @Transactional(readOnly = true)
    public RideDto getRideById(String id) {
        Ride ride = rideValidationManager.getRideByIdIfExists(id);
        return rideMapper.handleEntity(ride);
    }

    private Query buildQuery(RideFilterRequest filterRequest) {
        Criteria criteria = new Criteria();
        addCriteriaIfNotNull(criteria, createdAt, filterRequest.createdAt());
        addCriteriaIfNotNull(criteria, driverId, filterRequest.driverId());
        addCriteriaIfNotNull(criteria, passengerId, filterRequest.passengerId());
        addCriteriaIfNotNull(criteria, status, filterRequest.status());
        addCriteriaIfNotNull(criteria, fareType, filterRequest.fareType());
        if (filterRequest.sortBy() != null) {
            criteria.and(filterRequest.sortBy().getFieldName()).ne(null);
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
                        filterRequest.order().toString()),
                filterRequest.sortBy().getFieldName());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }

    public RidePackageDto createRidePackage(List<RideDto> rides, long totalElements, RideFilterRequest filterRequest) {
        return new RidePackageDto(
                rides,
                totalElements,
                filterRequest.page(),
                rides.size(),
                (int) Math.ceil((double) totalElements / filterRequest.size())
        );
    }
}
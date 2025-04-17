package com.internship.driverservice.service.specification;

import com.internship.driverservice.dto.transfer.CarFilterRequest;
import com.internship.driverservice.entity.Car;
import com.internship.driverservice.entity.DriverProfile;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.internship.driverservice.entity.Car.Fields.brand;
import static com.internship.driverservice.entity.Car.Fields.carNumber;
import static com.internship.driverservice.entity.Car.Fields.driverProfile;
import static com.internship.driverservice.entity.DriverProfile.Fields.profileId;

@Service
public class CarSpecification {

    public Specification<Car> createFilterSpecification(CarFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.getDriverId())
                    .ifPresent(driverId -> {
                        Join<Car, DriverProfile> driverProfileJoin = root.join(driverProfile, JoinType.INNER);
                        predicates.add(cb.equal(driverProfileJoin.get(profileId), driverId));
                    });
            addLikePredicate(predicates, cb, root.get(carNumber), filter.getCarNumber());
            addLikePredicate(predicates, cb, root.get(brand), filter.getBrand());

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addLikePredicate(List<Predicate> predicates,
                                  CriteriaBuilder cb,
                                  Expression<String> expression,
                                  String value) {
        Optional.ofNullable(value)
                .ifPresent(val -> predicates.add(cb.like(cb.lower(expression), "%" + val + "%")));
    }
}
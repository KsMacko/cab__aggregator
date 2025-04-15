package com.internship.driver_service.service.specification;

import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.entity.Rate;
import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.internship.driver_service.entity.Car.Fields.carNumber;
import static com.internship.driver_service.entity.DriverProfile.Fields.car;
import static com.internship.driver_service.entity.DriverProfile.Fields.driverStatus;
import static com.internship.driver_service.entity.DriverProfile.Fields.fareType;
import static com.internship.driver_service.entity.DriverProfile.Fields.firstName;
import static com.internship.driver_service.entity.DriverProfile.Fields.lastName;
import static com.internship.driver_service.entity.DriverProfile.Fields.phone;
import static com.internship.driver_service.entity.DriverProfile.Fields.profileId;
import static com.internship.driver_service.entity.Rate.Fields.driver;
import static com.internship.driver_service.entity.Rate.Fields.value;

@Service
public class DriverSpecificationService {

    public Specification<DriverProfile> createFilterSpecification(DriverFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            addLikePredicate(predicates, cb, root.get(phone), filter.getPhone());
            addLikePredicate(predicates, cb, root.get(firstName), filter.getFirstName());
            addLikePredicate(predicates, cb, root.get(lastName), filter.getLastName());

            Optional.ofNullable(filter.getCarNumber())
                    .ifPresent(carNumberToFilter -> {
                        Join<DriverProfile, Car> carJoin = root.join(car, JoinType.LEFT);
                        predicates.add(cb.like(carJoin.get(carNumber), "%" + carNumberToFilter + "%"));
                    });
            addEnumPredicate(predicates, cb, root.get(driverStatus), filter.getStatus(), DriverStatus.class);
            addEnumPredicate(predicates, cb, root.get(fareType), filter.getFareType(), FareType.class);
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Rate> rateRoot = subquery.from(Rate.class);
            subquery.select(cb.avg(rateRoot.get(value)))
                    .where(cb.equal(rateRoot.get(driver).get(profileId), root.get(profileId)));
            addRatePredicate(predicates, cb, subquery, filter.getRate());

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addLikePredicate(List<Predicate> predicates,
                                  CriteriaBuilder cb,
                                  Expression<String> expression,
                                  String value) {
        Optional.ofNullable(value)
                .ifPresent(val -> predicates.add(cb.like(expression, "%" + val + "%")));
    }

    private void addRatePredicate(List<Predicate> predicates,
                                  CriteriaBuilder cb,
                                  Subquery<Double> subquery,
                                  Byte rateToFilter) {
        Optional.ofNullable(rateToFilter)
                .ifPresent(rate -> predicates.add(cb.equal(cb.floor(subquery.getSelection()), rate)));
    }

    private <T extends Enum<T>> void addEnumPredicate(List<Predicate> predicates,
                                                      CriteriaBuilder cb,
                                                      Expression<T> expression,
                                                      String filterValue,
                                                      Class<T> enumClass) {
        Optional.ofNullable(filterValue)
                .ifPresent(value -> {
                    T enumValue = Enum.valueOf(enumClass, value);
                    predicates.add(cb.equal(expression, enumValue));
                });
    }
}
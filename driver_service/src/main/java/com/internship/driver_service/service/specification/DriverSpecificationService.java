package com.internship.driver_service.service.specification;

import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.entity.Rate;
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

            addLikePredicate(predicates, cb, root.get(phone), filter.phone());
            addLikePredicate(predicates, cb, root.get(firstName), filter.firstName());
            addLikePredicate(predicates, cb, root.get(lastName), filter.lastName());

            Optional.ofNullable(filter.carNumber()).ifPresent(carNumberToFilter -> {
                Join<DriverProfile, Car> carJoin = root.join(car, JoinType.LEFT);
                predicates.add(cb.like(carJoin.get(carNumber), "%" + carNumberToFilter + "%"));
            });

            addEqualPredicate(predicates, cb, root.get(driverStatus), filter.status());
            addEqualPredicate(predicates, cb, root.get(fareType), filter.fareType());

            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Rate> rateRoot = subquery.from(Rate.class);
            subquery.select(cb.avg(rateRoot.get(value)))
                    .where(cb.equal(rateRoot.get(driver).get(profileId), root.get(profileId)));

            addRatePredicate(predicates, cb, subquery, filter.rate());

            query.multiselect(
                    root.get(profileId),
                    root.get(firstName),
                    root.get(lastName),
                    root.get(phone),
                    root.get(fareType),
                    root.get(driverStatus),
                    getAverageRateSubquery(cb, subquery)
            );

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

    private <T> void addEqualPredicate(List<Predicate> predicates,
                                       CriteriaBuilder cb,
                                       Expression<T> expression,
                                       T value) {
        Optional.ofNullable(value)
                .ifPresent(val -> predicates.add(cb.equal(expression, val)));
    }

    private void addRatePredicate(List<Predicate> predicates,
                                  CriteriaBuilder cb,
                                  Subquery<Double> subquery,
                                  Byte rateToFilter) {
        Optional.ofNullable(rateToFilter)
                .ifPresent(rate -> predicates.add(cb.equal(cb.floor(subquery.getSelection()), rate)));
    }

    private Expression<Double> getAverageRateSubquery(CriteriaBuilder cb,
                                                      Subquery<Double> subquery) {
        return cb.floor(subquery.getSelection());
    }
}
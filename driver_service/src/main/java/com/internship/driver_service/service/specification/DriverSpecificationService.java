package com.internship.driver_service.service.specification;

import static com.internship.driver_service.entity.DriverProfile.Fields.*;
import static com.internship.driver_service.entity.Rate.Fields.*;
import com.internship.driver_service.dto.transfer_objects.DriverFilterRequest;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.entity.Rate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverSpecificationService {

    public Specification<DriverProfile> createFilterSpecification(DriverFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(filter.phone())
                    .ifPresent(phoneToFilter -> predicates.add(
                            cb.like(root.get(phone), "%" + phoneToFilter + "%")));
            Optional.ofNullable(filter.carNumber())
                    .ifPresent(carNumberToFilter -> predicates.add(
                            cb.like(root.get(carNumber), "%" + carNumberToFilter + "%")));
            Optional.ofNullable(filter.firstName())
                    .ifPresent(firstNameToFilter -> predicates.add(
                            cb.like(cb.lower(root.get(firstName)), "%" + firstNameToFilter.toLowerCase() + "%")));
            Optional.ofNullable(filter.lastName())
                    .ifPresent(lastNameToFilter -> predicates.add(
                            cb.like(cb.lower(root.get(lastName)), "%" + lastNameToFilter.toLowerCase() + "%")));
            Optional.ofNullable(filter.status())
                    .ifPresent(statusToFilter -> predicates.add(
                            cb.equal(root.get(driverStatus), statusToFilter)));
            Optional.ofNullable(filter.fareType())
                    .ifPresent(fareTypeToFilter -> predicates.add(
                            cb.equal(root.get(fareType), fareTypeToFilter)));

            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Rate> rateRoot = subquery.from(Rate.class);
            subquery.select(cb.avg(rateRoot.get(value)))
                    .where(cb.equal(rateRoot.get(driver).get(profileId), root.get(id)));
            Optional.ofNullable(filter.rate()).ifPresent(rateToFilter -> {
                predicates.add(cb.equal(cb.floor(subquery.getSelection()), rateToFilter));
            });
            query.multiselect(
                    root.get(id),
                    root.get(firstName),
                    root.get(lastName),
                    root.get(phone),
                    root.get(carNumber),
                    root.get(carDescription),
                    root.get(fareType),
                    root.get(driverStatus),
                    cb.floor(subquery.getSelection())
            );

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
package com.internship.driver_service.service.specification;

import static com.internship.driver_service.entity.DriverProfile.Fields.*;
import static com.internship.driver_service.entity.Rate.Fields.*;
import com.internship.driver_service.dto.DriverFilterRequest;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.entity.Rate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverSpecificationService {

    public Specification<DriverProfile> createFilterSpecification(DriverFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.phone() != null) {
                predicates.add(cb.like(root.get(phone), "%" + filter.phone() + "%"));
            }
            if (filter.carNumber() != null) {
                predicates.add(cb.like(root.get(carNumber), "%" + filter.carNumber() + "%"));
            }
            if (filter.firstName() != null) {
                predicates.add(cb.like(cb.lower(root.get(firstName)), "%" + filter.firstName().toLowerCase() + "%"));
            }
            if (filter.lastName() != null) {
                predicates.add(cb.like(cb.lower(root.get(lastName)), "%" + filter.lastName().toLowerCase() + "%"));
            }
            if (filter.status() != null) {
                predicates.add(cb.equal(root.get(driverStatus), filter.status()));
            }
            if (filter.fareType() != null) {
                predicates.add(cb.equal(root.get(fareType), filter.fareType()));
            }
            if (filter.rate() != null) {
                Subquery<Byte> subquery = query.subquery(Byte.class);
                Root<Rate> rateRoot = subquery.from(Rate.class);
                subquery.select(cb.function("FLOOR", Byte.class, cb.avg(rateRoot.get(value))))
                        .where(cb.equal(rateRoot.get(driver).get(profileId), root.get(id)));

                predicates.add(cb.equal(subquery, filter.rate()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
package com.internship.passenger_service.service.specification;

import com.internship.passenger_service.dto.transfer.ProfileFilterRequest;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.entity.Rate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.internship.passenger_service.entity.PassengerProfile.Fields.email;
import static com.internship.passenger_service.entity.PassengerProfile.Fields.phone;
import static com.internship.passenger_service.entity.PassengerProfile.Fields.profileId;
import static com.internship.passenger_service.entity.Rate.Fields.passenger;
import static com.internship.passenger_service.entity.Rate.Fields.value;

@Service
public class PassengerProfileSpecification {

    public Specification<PassengerProfile> filterBy(ProfileFilterRequest filter) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            addEqualPredicate(predicates, criteriaBuilder, root.get(email), filter.getEmail());
            addEqualPredicate(predicates, criteriaBuilder, root.get(phone), filter.getPhone());

            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Rate> rateRoot = subquery.from(Rate.class);
            subquery.select(criteriaBuilder.avg(rateRoot.get(value)))
                    .where(criteriaBuilder.equal(rateRoot.get(passenger).get(profileId), root.get(profileId)));

            addRatePredicate(predicates, criteriaBuilder, subquery, filter.getRate());

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addEqualPredicate(List<Predicate> predicates,
                                   CriteriaBuilder cb,
                                   Expression<String> expression,
                                   String value) {
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
}
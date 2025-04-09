package com.internship.passenger_service.service.specification;

import com.internship.passenger_service.dto.transfer_objects.ProfileFilterRequest;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.entity.Rate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.internship.passenger_service.entity.PassengerProfile.Fields.*;
import static com.internship.passenger_service.entity.Rate.Fields.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerProfileSpecification {

    public Specification<PassengerProfile> filterBy(ProfileFilterRequest filter) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.email())
                    .ifPresent(emailToFilter -> predicates.add(criteriaBuilder.equal(root.get(email), email)));

            Optional.ofNullable(filter.phone())
                    .ifPresent(phoneToFilter -> predicates.add(criteriaBuilder.equal(root.get(phone), phone)));

            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Rate> rateRoot = subquery.from(Rate.class);
            subquery.select(criteriaBuilder.avg(rateRoot.get(value)))
                    .where(criteriaBuilder.equal(rateRoot.get(passenger).get(profileId), root.get(profileId)));

            Optional.ofNullable(filter.rate()).ifPresent(rate -> {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.floor(subquery.getSelection()), rate));
            });

            query.multiselect(
                    root.get(profileId),
                    root.get(firstName),
                    root.get(email),
                    root.get(phone),
                    criteriaBuilder.floor(subquery.getSelection())
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
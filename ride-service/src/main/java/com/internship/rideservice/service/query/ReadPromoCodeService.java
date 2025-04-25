package com.internship.rideservice.service.query;

import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.dto.mapper.PromoCodeMapper;
import com.internship.rideservice.dto.transfer.PromoCodeFilterRequest;
import com.internship.rideservice.dto.transfer.PromoCodePackageDto;
import com.internship.rideservice.entity.PromoCode;
import com.internship.rideservice.enums.PromoCodeFieldsToFilter;
import com.internship.rideservice.util.validators.PromoCodeValidationManager;
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

import static com.internship.rideservice.entity.PromoCode.Fields.createdAt;
import static com.internship.rideservice.entity.PromoCode.Fields.validUntil;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ReadPromoCodeService {
    private final PromoCodeMapper promoCodeMapper;
    private final MongoTemplate mongoTemplate;
    private final PromoCodeValidationManager promoCodeValidationManager;

    @Transactional(readOnly = true)
    public PromoCodePackageDto getFilteredPromoCodes(PromoCodeFilterRequest filterRequest) {
        Query query = buildQuery(filterRequest);
        query.with(createPageableObject(filterRequest));
        List<PromoCode> promoCodes = mongoTemplate.find(query, PromoCode.class);
        long totalElements = mongoTemplate.count(query, PromoCode.class);
        List<ResponsePromoCodeDto> promoCodesDto = promoCodes.stream()
                .map(promoCodeMapper::handleEntity)
                .toList();

        return new PromoCodePackageDto(
                promoCodesDto,
                totalElements,
                filterRequest.getPage(),
                promoCodesDto.size(),
                (int) Math.ceil((double) totalElements / filterRequest.getSize())
        );
    }

    @Transactional(readOnly = true)
    public ResponsePromoCodeDto getPromoCodeById(String id) {
        return promoCodeMapper.handleEntity(promoCodeValidationManager.getPromoCodeByIdIfExists(id));
    }

    @Transactional(readOnly = true)
    public PromoCode getPromoCodeCurrentByCode(String code) {
        return promoCodeValidationManager.getCurrentPromoCode(code);
    }

    private Query buildQuery(PromoCodeFilterRequest filterRequest) {
        Criteria criteria = new Criteria();
        addCriteriaIfNotNull(criteria, createdAt, filterRequest.getCreatedDate());
        addCriteriaIfNotNull(criteria, validUntil, filterRequest.getValidDate());
        return new Query(criteria);
    }

    private void addCriteriaIfNotNull(Criteria criteria, String fieldName, Object value) {
        if (nonNull(value)) {
            criteria.and(fieldName).is(value);
        }
    }

    private Pageable createPageableObject(PromoCodeFilterRequest filterRequest) {
        Sort sort = Sort.by(
                Sort.Direction.fromString(filterRequest.getOrder()),
                PromoCodeFieldsToFilter.valueOf(filterRequest.getSortBy()).getFieldName());
        return PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);
    }
}
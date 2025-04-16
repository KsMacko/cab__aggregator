package com.internship.ride_service.service.query;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.mapper.PromoCodeMapper;
import com.internship.ride_service.dto.transfer.PromoCodeFilterRequest;
import com.internship.ride_service.dto.transfer.PromoCodePackageDto;
import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.enums.PromoCodeFieldsToFilter;
import com.internship.ride_service.util.validators.PromoCodeValidationManager;
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

import static com.internship.ride_service.entity.PromoCode.Fields.createdAt;
import static com.internship.ride_service.entity.PromoCode.Fields.validUntil;
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
        List<PromoCodeDto> promoCodesDto = promoCodes.stream()
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
    public PromoCodeDto getPromoCodeById(String id) {
        return promoCodeMapper.handleEntity(promoCodeValidationManager.getPromoCodeByIdIfExists(id));
    }

    @Transactional(readOnly = true)
    public PromoCodeDto getPromoCodeCurrentByCode(String code) {
        return promoCodeMapper.handleEntity(promoCodeValidationManager.getCurrentPromoCode(code));
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
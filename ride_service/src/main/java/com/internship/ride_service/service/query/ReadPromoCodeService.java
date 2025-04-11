package com.internship.ride_service.service.query;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.mapper.PromoCodeMapper;
import com.internship.ride_service.dto.transfer.PromoCodeFilterRequest;
import com.internship.ride_service.dto.transfer.PromoCodePackageDto;
import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.repo.PromoCodeRepo;
import com.internship.ride_service.util.PromoCodeValidationManager;
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
    private final PromoCodeRepo promoCodeRepo;
    private final MongoTemplate mongoTemplate;
    private final PromoCodeValidationManager promoCodeValidationManager;

    @Transactional(readOnly = true)
    public PromoCodePackageDto getFilteredPromoCodes(PromoCodeFilterRequest filterRequest) {
        Query query = buildQuery(filterRequest);
        query.with(createPageableObject(filterRequest));
        List<PromoCode> promoCodes = mongoTemplate.find(query, PromoCode.class);
        long totalElements = mongoTemplate.count(query, PromoCode.class);
        List<PromoCodeDto> promoCodesDto = promoCodes.stream()
                .map(PromoCodeMapper.converter::handleEntity)
                .toList();

        return new PromoCodePackageDto(
                promoCodesDto,
                totalElements,
                filterRequest.page(),
                promoCodesDto.size(),
                (int) Math.ceil((double) totalElements / filterRequest.size())
        );
    }

    @Transactional(readOnly = true)
    public PromoCodeDto getPromoCodeById(String id) {
        return PromoCodeMapper.converter.handleEntity(promoCodeValidationManager.getPromoCodeByIdIfExists(id));
    }

    @Transactional(readOnly = true)
    public PromoCodeDto getPromoCodeCurrentByCode(String code) {
        return PromoCodeMapper.converter.handleEntity(promoCodeValidationManager.getCurrentPromoCode(code));
    }

    private Query buildQuery(PromoCodeFilterRequest filterRequest) {
        Criteria criteria = new Criteria();
        addCriteriaIfNotNull(criteria, createdAt, filterRequest.createdDate());
        addCriteriaIfNotNull(criteria, validUntil, filterRequest.validDate());
        return new Query(criteria);
    }

    private void addCriteriaIfNotNull(Criteria criteria, String fieldName, Object value) {
        if (nonNull(value))
            criteria.and(fieldName).is(value);
    }

    private Pageable createPageableObject(PromoCodeFilterRequest filterRequest) {
        Sort sort = Sort.by(
                Sort.Direction.fromString(filterRequest.order().toString()),
                filterRequest.sortBy().getFieldName());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }
}
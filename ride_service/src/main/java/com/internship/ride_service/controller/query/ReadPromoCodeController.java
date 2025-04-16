package com.internship.ride_service.controller.query;

import com.internship.ride_service.controller.doc.ReadPromoDoc;
import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.transfer.PromoCodePackageDto;
import com.internship.ride_service.dto.transfer.PromoCodeFilterRequest;
import com.internship.ride_service.service.query.ReadPromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promo-codes")
@RequiredArgsConstructor
public class ReadPromoCodeController implements ReadPromoDoc {

    private final ReadPromoCodeService readPromoCodeService;

    @Override
    public PromoCodePackageDto getFilteredPromoCodes(@ModelAttribute PromoCodeFilterRequest filterRequest) {
        return readPromoCodeService.getFilteredPromoCodes(filterRequest);
    }

    @Override
    public PromoCodeDto getPromoCode(@PathVariable String code) {
        return  readPromoCodeService.getPromoCodeCurrentByCode(code);
    }
}
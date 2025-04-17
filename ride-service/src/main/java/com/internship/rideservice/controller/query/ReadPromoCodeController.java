package com.internship.rideservice.controller.query;

import com.internship.rideservice.controller.doc.ReadPromoDoc;
import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.dto.transfer.PromoCodePackageDto;
import com.internship.rideservice.dto.transfer.PromoCodeFilterRequest;
import com.internship.rideservice.service.query.ReadPromoCodeService;
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
    public ResponsePromoCodeDto getPromoCode(@PathVariable String code) {
        return  readPromoCodeService.getPromoCodeCurrentByCode(code);
    }
}
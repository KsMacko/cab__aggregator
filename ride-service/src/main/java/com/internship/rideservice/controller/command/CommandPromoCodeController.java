package com.internship.rideservice.controller.command;

import com.internship.rideservice.controller.doc.CommandPromoDoc;
import com.internship.rideservice.dto.mapper.PromoCodeMapper;
import com.internship.rideservice.dto.request.RequestPromoCodeDto;
import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.entity.PromoCode;
import com.internship.rideservice.service.command.CommandPromoCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/promo-codes")
@RequiredArgsConstructor
public class CommandPromoCodeController implements CommandPromoDoc {

    private final CommandPromoCodeService commandPromoCodeService;
    private final PromoCodeMapper promoCodeMapper;

    @Override
    public ResponseEntity<ResponsePromoCodeDto> createPromoCode(@Valid @RequestBody RequestPromoCodeDto promoCodeDto) {
        PromoCode createdPromoCode = commandPromoCodeService.createPromoCode(promoCodeDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(createdPromoCode.getPromoCode())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(promoCodeMapper.handleEntity(createdPromoCode));
    }

    @Override
    public ResponseEntity<Void> deletePromoCode(@PathVariable String id) {
        commandPromoCodeService.deletePromoCode(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ResponsePromoCodeDto> updatePromoCode(@RequestParam String promoCodeId,
                                                @RequestBody RequestPromoCodeDto promoCodeDto) {
        return ResponseEntity.ok(promoCodeMapper.handleEntity(commandPromoCodeService.updatePromoCode(promoCodeId, promoCodeDto)));
    }
}
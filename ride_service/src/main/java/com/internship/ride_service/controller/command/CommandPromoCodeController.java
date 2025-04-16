package com.internship.ride_service.controller.command;

import com.internship.ride_service.controller.doc.CommandPromoDoc;
import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.service.command.CommandPromoCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/promo-codes")
@RequiredArgsConstructor
public class CommandPromoCodeController implements CommandPromoDoc {

    private final CommandPromoCodeService commandPromoCodeService;

    @Override
    public ResponseEntity<PromoCodeDto> createPromoCode(@Valid @RequestBody PromoCodeDto promoCodeDto) {
        PromoCodeDto createdPromoCode = commandPromoCodeService.createPromoCode(promoCodeDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(createdPromoCode.promoCode())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdPromoCode);
    }

    @Override
    public ResponseEntity<Void> deletePromoCode(@PathVariable String id) {
        commandPromoCodeService.deletePromoCode(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public PromoCodeDto updatePromoCode(@RequestBody PromoCodeDto promoCodeDto) {
        return commandPromoCodeService.updatePromoCode(promoCodeDto);
    }
}
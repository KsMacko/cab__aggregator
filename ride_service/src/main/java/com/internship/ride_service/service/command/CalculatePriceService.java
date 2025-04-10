package com.internship.ride_service.service.command;

import static java.util.Objects.nonNull;
import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.util.FareValidationManager;
import com.internship.ride_service.util.PromoCodeValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CalculatePriceService {
    private final FareValidationManager fareValidationManager;
    private final PromoCodeValidationManager promoCodeValidationManager;

    public BigDecimal ReCalculatePrice(Ride ride) {
        Fare fare = fareValidationManager.getFareIfExists(ride.getFareType());
        BigDecimal currentAmount = ride.getPrice();
        BigDecimal amountByTime = BigDecimal.valueOf(Duration.between(
                        ride.getStartTime(),
                        ride.getEndTime()
                ).toMinutes())
                .multiply(fare.getPricePerMin());
        BigDecimal maxPrice = currentAmount.max(amountByTime);
        PromoCode promoCode = promoCodeValidationManager.getCurrentPromoCode(ride.getPromoCode());
        BigDecimal totalPrice;
        if(nonNull(promoCode)){
            totalPrice=maxPrice.multiply(
                    BigDecimal.ONE.subtract(
                            BigDecimal.valueOf(promoCode.getDiscount())
                                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)));
        }
        else totalPrice=maxPrice;
        long waitingTime= Duration.between(ride.getStartWaitingTime(), ride.getStartTime()).toMinutes();
        if( waitingTime > fare.getFreeWaiting()) {
            totalPrice = totalPrice.add(fare.getPaidWaitingPrice()
                    .multiply(BigDecimal.valueOf(waitingTime-fare.getFreeWaiting()))
            );
        }
        return totalPrice;
    }
}

package com.codingshuttle.airBnb.airBnbCloneProject.stratergy;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

 @RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        LocalDate today = LocalDate.now();
         if( !inventory.getDate().isBefore(today) && inventory.getDate().isBefore(today.plus(7, ChronoUnit.DAYS))){
             price = price.multiply(BigDecimal.valueOf(1.15));
         }
        return price;
    }
}

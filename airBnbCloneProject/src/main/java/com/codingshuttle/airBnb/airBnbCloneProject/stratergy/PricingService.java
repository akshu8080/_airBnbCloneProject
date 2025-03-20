package com.codingshuttle.airBnb.airBnbCloneProject.stratergy;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingService {
    public BigDecimal calculateDynamicPricing(Inventory inventory){
         PricingStrategy pricingStrategy = new BasePricingStrategy();

         //Apply the additional strategies
        pricingStrategy  = new SurgePricingStrategy(pricingStrategy);
        pricingStrategy  = new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy  = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy  = new HolidayPricingStrategy(pricingStrategy);


        return pricingStrategy.calculatePrice(inventory);




    }

    //Return the sum of price of this inventory List
    public BigDecimal calculateTotalPrice(List<Inventory> inventoryList){
         return inventoryList.stream()
                .map(this::calculateDynamicPricing)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

    }
}

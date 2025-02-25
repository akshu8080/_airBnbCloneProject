package com.codingshuttle.airBnb.airBnbCloneProject.stratergy;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BasePricingStrategy implements  PricingStrategy{

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return  inventory.getRoom().getBasePrice();
    }
}

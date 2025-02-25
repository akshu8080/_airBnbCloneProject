package com.codingshuttle.airBnb.airBnbCloneProject.stratergy;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

  public  interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}

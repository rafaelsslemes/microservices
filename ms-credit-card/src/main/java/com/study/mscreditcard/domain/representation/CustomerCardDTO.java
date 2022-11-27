package com.study.mscreditcard.domain.representation;

import java.math.BigDecimal;

import com.study.mscreditcard.domain.Brand;
import com.study.mscreditcard.domain.CustomerCard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCardDTO {

    private String name;
    private Brand brand;
    BigDecimal limit;

    public static CustomerCardDTO fromModel(CustomerCard customerCard) {
        return new CustomerCardDTO(
            customerCard.getCreditCard().getName(),
            customerCard.getCreditCard().getBrand(),
            customerCard.getCardLimit());
        
    }
}

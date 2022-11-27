package com.study.mscreditcard.domain.representation;

import java.math.BigDecimal;

import com.study.mscreditcard.domain.Brand;
import com.study.mscreditcard.domain.CreditCard;

import lombok.Data;

@Data
public class CreditCardDTO {

    private String name;
    private Brand brand;
    private BigDecimal requiredIncome;
    private BigDecimal baseLimit;

    public CreditCard toModel() {
        return new CreditCard(name, requiredIncome, baseLimit, brand);
    }
}

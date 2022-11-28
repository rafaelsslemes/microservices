package com.study.mscreditappraiser.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCard {
    
    private Long id;
    private String name;
    private String brand;
    private BigDecimal baseLimit;
    
}

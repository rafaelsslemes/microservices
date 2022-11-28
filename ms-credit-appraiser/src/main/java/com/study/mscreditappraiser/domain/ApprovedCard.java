package com.study.mscreditappraiser.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApprovedCard {
    
    private String cardName;
    private String cardBrand;
    private BigDecimal cardLimit;
}

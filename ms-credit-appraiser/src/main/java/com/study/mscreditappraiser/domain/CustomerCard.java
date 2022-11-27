package com.study.mscreditappraiser.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerCard {
   
    private Long id;
    private String doc;
    private BigDecimal cardLimit;

}

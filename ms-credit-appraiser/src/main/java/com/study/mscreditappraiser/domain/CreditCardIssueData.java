package com.study.mscreditappraiser.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreditCardIssueData {
    
    private Long cardId;
    private String customerDoc;
    private String customerAddress;
    private BigDecimal cardLimit; 
}

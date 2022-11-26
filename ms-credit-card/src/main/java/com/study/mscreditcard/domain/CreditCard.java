package com.study.mscreditcard.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CreditCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "card_seq")
    private Long id;
    private String name;
    private BigDecimal requiredIncome;
    private BigDecimal baseLimit;
    
    @Enumerated(EnumType.STRING)
    private Brand brand;

    public CreditCard(String name, BigDecimal requiredIncome, BigDecimal baseLimit, Brand brand) {
        this.name = name;
        this.requiredIncome = requiredIncome;
        this.baseLimit = baseLimit;
        this.brand = brand;
    }

    
}

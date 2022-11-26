package com.study.mscreditcard.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.mscreditcard.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

    List<CreditCard> findByRequiredIncomeLessThanEqual(BigDecimal incomeBigDecimal);
    
}

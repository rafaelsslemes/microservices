package com.study.mscreditcard.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mscreditcard.domain.CreditCard;
import com.study.mscreditcard.repository.CreditCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    
    private final CreditCardRepository repository;

    @Transactional
    public CreditCard save(CreditCard card) {
        return repository.save(card);
    }

    public List<CreditCard> getCompatibleCards(Long customerIncome) {
        var incomeBigDecimal = BigDecimal.valueOf(customerIncome);

        return repository.findByRequiredIncomeLessThanEqual(incomeBigDecimal);
    }

    public Optional <CreditCard> findById(Long id) {
        return repository.findById(id);
    }
}

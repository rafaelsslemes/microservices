package com.study.mscreditcard.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mscreditcard.domain.CustomerCard;
import com.study.mscreditcard.repository.CustomerCardRepository;

@Service
public class CustomerCardService {

    @Autowired
    private CustomerCardRepository repository;
    
    public List<CustomerCard> getCardByDoc(String doc){
        return repository.findByDoc(doc);
    }
}

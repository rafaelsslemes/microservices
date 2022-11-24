package com.study.mscustomers.application;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mscustomers.domain.Customer;
import com.study.mscustomers.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer){
        return repository.save(customer);
    }
    
    public Optional<Customer> getByDoc(String doc){
        return repository.getByDoc(doc);
    }
}
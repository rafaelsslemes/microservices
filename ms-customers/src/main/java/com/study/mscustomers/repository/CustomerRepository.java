package com.study.mscustomers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.mscustomers.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> getByDoc(String doc);
    
}

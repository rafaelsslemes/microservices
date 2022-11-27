package com.study.mscreditcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.mscreditcard.domain.CustomerCard;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long>{
    
    public List<CustomerCard> findByDoc(String doc);
}

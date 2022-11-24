package com.study.mscustomers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_seq")
    private Long id;
    private String name;
    private String doc;
    private Integer age;

    public Customer(String doc, String name, Integer age) {
        this.name = name;
        this.doc = doc;
        this.age = age;
    }

    
}

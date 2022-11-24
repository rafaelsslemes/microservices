package com.study.mscustomers.application.representation;

import com.study.mscustomers.domain.Customer;

import lombok.Data;

@Data
public class CustomerSaveRequest {
    private String name;
    private String doc;
    private Integer age;

    public Customer toModel(){
        return new Customer(doc, name, age);
    }
}

package com.study.mscreditappraiser.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.mscreditappraiser.domain.CustomerData;
import com.study.mscreditappraiser.domain.CustomerState;
import com.study.mscreditappraiser.infra.CustomerResourceClient;

@Service
public class CreditAppraiserService {

    @Autowired
    private CustomerResourceClient customerResourceClient;

    public CustomerState getCustomerState(String doc) {
        ResponseEntity<CustomerData> customerResponse = customerResourceClient.getCustomer(doc);

        System.out.println(customerResponse);
        return CustomerState.builder()
            .customerData(customerResponse.getBody())
            .build();
    }
}

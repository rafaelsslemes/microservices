package com.study.mscreditappraiser.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;
import com.study.mscreditappraiser.domain.CustomerCard;
import com.study.mscreditappraiser.domain.CustomerData;
import com.study.mscreditappraiser.domain.CustomerState;
import com.study.mscreditappraiser.infra.CreditCardResourceClient;
import com.study.mscreditappraiser.infra.CustomerResourceClient;

@Service
public class CreditAppraiserService {

    @Autowired
    private CustomerResourceClient customerResourceClient;

    @Autowired
    private CreditCardResourceClient creditCardResourceClient;

    public CustomerState getCustomerState(String doc) {
        ResponseEntity<CustomerData> customerResponse = customerResourceClient.getCustomer(doc);
        ResponseEntity<List<CustomerCard>> creditCardResponse = creditCardResourceClient.getCustomerCards(doc);

        return CustomerState.builder()
            .customerData(customerResponse.getBody())
            .cards(creditCardResponse.getBody())
            .build();
    }
}

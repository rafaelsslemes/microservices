package com.study.mscreditappraiser.application;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.mscreditappraiser.application.exception.CustomerNotFoundException;
import com.study.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.study.mscreditappraiser.domain.CustomerCard;
import com.study.mscreditappraiser.domain.CustomerData;
import com.study.mscreditappraiser.domain.CustomerState;
import com.study.mscreditappraiser.infra.CreditCardResourceClient;
import com.study.mscreditappraiser.infra.CustomerResourceClient;

import feign.FeignException.FeignClientException;

@Service
public class CreditAppraiserService {

    @Autowired
    private CustomerResourceClient customerResourceClient;

    @Autowired
    private CreditCardResourceClient creditCardResourceClient;

    public CustomerState getCustomerState(String doc) 
        throws CustomerNotFoundException, MicroserviceCommunicationException {
        
        try{

            ResponseEntity<CustomerData> customerResponse = customerResourceClient.getCustomer(doc);
            ResponseEntity<List<CustomerCard>> creditCardResponse = creditCardResourceClient.getCustomerCards(doc);
            
            return CustomerState.builder()
            .customerData(customerResponse.getBody())
            .cards(creditCardResponse.getBody())
            .build();
        
        }catch(FeignClientException e){
            if(e.status() == HttpStatus.SC_NOT_FOUND){
                throw new CustomerNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.status(), e.getMessage());
        }

        
    }
}
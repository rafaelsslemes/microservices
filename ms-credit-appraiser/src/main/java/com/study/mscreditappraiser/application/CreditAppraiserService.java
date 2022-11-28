package com.study.mscreditappraiser.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.study.mscreditappraiser.application.exception.CustomerNotFoundException;
import com.study.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.study.mscreditappraiser.domain.AppraisalResult;
import com.study.mscreditappraiser.domain.ApprovedCard;
import com.study.mscreditappraiser.domain.CreditCard;
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

    public AppraisalResult creditAppraisal(String customerDoc, Long customerIncome)
        throws CustomerNotFoundException, MicroserviceCommunicationException{
        
            try{
                ResponseEntity<CustomerData> customerResponse = customerResourceClient.getCustomer(customerDoc);
                ResponseEntity<List<CreditCard>> creditCardResponse = creditCardResourceClient.getCompatibleCards(customerIncome);
                
                var compatibleCards = creditCardResponse.getBody();
                var customerData = customerResponse.getBody();
                BigDecimal creditFactor =  BigDecimal.valueOf(customerData.getAge()/10);

                List<ApprovedCard> approvedCards = compatibleCards.stream().map(card -> {

                    BigDecimal approvedLimit = card.getBaseLimit().multiply(creditFactor);
                    return new ApprovedCard(card.getName(), card.getBrand(), approvedLimit);

                }).collect(Collectors.toList());

                return new AppraisalResult(approvedCards);
            
            }catch(FeignClientException e){
                if(e.status() == HttpStatus.SC_NOT_FOUND){
                    throw new CustomerNotFoundException();
                }
                throw new MicroserviceCommunicationException(e.status(), e.getMessage());
            }
    }
}
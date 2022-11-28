package com.study.mscreditappraiser.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.mscreditappraiser.application.exception.CustomerNotFoundException;
import com.study.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.study.mscreditappraiser.domain.CustomerState;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("credit-appraisal")
public class CreditAppraiserController {
    
    @Autowired
    private CreditAppraiserService creditAppraisalService;

    @GetMapping
    public String getStatus() {
        return "Credit Appraiser OK";
    }
    
    @GetMapping(value="customer-state")
    public ResponseEntity getCustomerState(@RequestParam String doc) {
        CustomerState customerState;
        try {
            customerState = creditAppraisalService.getCustomerState(doc);
            return ResponseEntity.ok().body(customerState);
        
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
    
}

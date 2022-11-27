package com.study.mscreditappraiser.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CustomerState> getCustomerState(@RequestParam String doc) {
        CustomerState customerState = creditAppraisalService.getCustomerState(doc);
        return ResponseEntity.ok().body(customerState);
    }
    
}

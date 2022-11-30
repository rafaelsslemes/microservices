package com.study.mscreditappraiser.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.mscreditappraiser.application.exception.CreditCardIssueException;
import com.study.mscreditappraiser.application.exception.CustomerNotFoundException;
import com.study.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.study.mscreditappraiser.domain.AppraisalData;
import com.study.mscreditappraiser.domain.AppraisalResult;
import com.study.mscreditappraiser.domain.CreditCardIssueData;
import com.study.mscreditappraiser.domain.CreditCardIssueProtocol;
import com.study.mscreditappraiser.domain.CustomerState;




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

    @PostMapping
    public ResponseEntity creditAppraisal(@RequestBody AppraisalData data) {

        try {
            AppraisalResult result = creditAppraisalService.creditAppraisal(
                data.getCustomerDoc(), data.getCustomerIncome());
            
            return ResponseEntity.ok().body(result);
        
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("credit-card-issue")
    public ResponseEntity issueCreditCard(@RequestBody CreditCardIssueData issueData){

        try {
            CreditCardIssueProtocol protocol = creditAppraisalService.issueCreditCard(issueData);
            return ResponseEntity.ok().body(protocol);

        } catch (CreditCardIssueException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
     
    
}

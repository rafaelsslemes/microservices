package com.study.mscreditappraiser.infra;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.mscreditappraiser.domain.CustomerCard;

@FeignClient(value = "ms-credit-cards", path = "cards")
public interface CreditCardResourceClient {
    
    @RequestMapping(params = "doc")
    public ResponseEntity<List<CustomerCard>> getCustomerCards(@RequestParam String doc);
    
}

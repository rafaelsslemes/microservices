package com.study.mscreditappraiser.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.mscreditappraiser.domain.CustomerData;

@FeignClient(value = "ms-customers", path = "customers")
public interface CustomerResourceClient {

    // Same method signature of ms-customers request controller
    @GetMapping(params = "doc")
    public ResponseEntity<CustomerData> getCustomer(@RequestParam("doc") String doc);
    
}

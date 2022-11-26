package com.study.mscustomers.application;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.study.mscustomers.application.representation.CustomerSaveRequest;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService service;
    
    @GetMapping
    public String getStatus(){
        log.info("Status Requested");
        return "OK";
    }

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody CustomerSaveRequest request){
        var customer = request.toModel();
        service.save(customer);
        URI headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("doc={doc}")
            .buildAndExpand(customer.getDoc())
            .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "doc")
    public ResponseEntity getCustomer(@RequestParam("doc") String doc){
        var customer = service.getByDoc(doc);

        if(customer.isPresent()){
            return ResponseEntity.ok().body(customer);
        }

        return ResponseEntity.notFound().build();
    }
}

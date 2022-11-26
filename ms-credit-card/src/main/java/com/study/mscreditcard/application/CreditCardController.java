package com.study.mscreditcard.application;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.study.mscreditcard.domain.CreditCard;
import com.study.mscreditcard.domain.representation.CreditCardSaveRequest;

@RestController
@RequestMapping("cards")
public class CreditCardController {

    @Autowired
    private CreditCardService service;
    
    public String getStatus(){
        return "OK";
    }

    @GetMapping(params = "id")
    public ResponseEntity<CreditCard> getCard(@RequestParam("id") Long id){
        Optional <CreditCard> creditCard = service.findById(id);

        if(creditCard.isPresent()){
            return ResponseEntity.ok().body(creditCard.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CreditCardSaveRequest request) {
        var creditCard = request.toModel();
        creditCard = service.save(creditCard);

        URI headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("id={id}")
            .buildAndExpand(creditCard.getId())
            .toUri();

        return ResponseEntity.created(headerLocation).build();
    }
}

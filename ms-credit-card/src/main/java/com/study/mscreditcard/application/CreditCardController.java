package com.study.mscreditcard.application;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.study.mscreditcard.domain.CustomerCard;
import com.study.mscreditcard.domain.representation.CreditCardDTO;
import com.study.mscreditcard.domain.representation.CustomerCardDTO;

@RestController
@RequestMapping("cards")
public class CreditCardController {

    @Autowired
    private CreditCardService cardService;

    @Autowired
    private CustomerCardService customerCardService;
    
    @GetMapping
    public String getStatus(){
        return "OK";
    }

    @GetMapping(params = "id")
    public ResponseEntity<CreditCard> getCard(@RequestParam("id") Long id){
        Optional <CreditCard> creditCard = cardService.findById(id);

        if(creditCard.isPresent()){
            return ResponseEntity.ok().body(creditCard.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CreditCardDTO request) {
        var creditCard = request.toModel();
        creditCard = cardService.save(creditCard);

        URI headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("id={id}")
            .buildAndExpand(creditCard.getId())
            .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @RequestMapping(params = "income")
    public ResponseEntity<List<CreditCard>> getCompatibleCards(@RequestParam("income") Long customerIncome) {
        List<CreditCard> cards = cardService.getCompatibleCards(customerIncome);

        return ResponseEntity.ok().body(cards);
    }

    @RequestMapping(params = "doc")
    public ResponseEntity<List<CustomerCardDTO>> getCustomerCards(@RequestParam String doc) {
        List<CustomerCard> cards = customerCardService.getCardByDoc(doc);
        List<CustomerCardDTO> cardDTOs = cards.stream().map(CustomerCardDTO::fromModel).collect(Collectors.toList());   
        
        return ResponseEntity.ok().body(cardDTOs);
    }
}

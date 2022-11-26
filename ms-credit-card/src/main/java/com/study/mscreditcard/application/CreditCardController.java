package com.study.mscreditcard.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CreditCardController {
    
    public String getStatus(){
        return "OK";
    }
}

package com.study.mscreditcard.infra;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mscreditcard.application.CustomerCardService;
import com.study.mscreditcard.application.exception.InexistentCreditCardException;
import com.study.mscreditcard.domain.CreditCardIssueData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor // Dismiss all @Autowired
@Slf4j
public class CreditCardIssueSubscriber {

    private final CustomerCardService customerCardService;

    @RabbitListener(queues = "${mq.queues.credit-card-issue}")
    public void receiveCreditCardIssue(@Payload String payload) {

        var mapper = new ObjectMapper();

        try {

            CreditCardIssueData creditCardIssueData = mapper.readValue(payload, CreditCardIssueData.class);
        
            try{
                customerCardService.issueCreditCard(creditCardIssueData);
            } catch (InexistentCreditCardException e) {
                log.error("The requested Credit Cards doesn't exist. CARD_ID {}", creditCardIssueData.getCardId());  
            }

        } catch (JsonMappingException e) {
            log.error("Error during JSON mapping", e);
        } catch (JsonProcessingException e) {
            log.error("Error during JSON processing", e);
        } 

    }
}

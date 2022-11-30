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

@Component
@RequiredArgsConstructor // Dismiss all @Autowired
public class CreditCardIssueSubscriber {

    private final CustomerCardService customerCardService;

    @RabbitListener(queues = "${mq.queues.credit-card-issue}")
    public void receiveCreditCardIssue(@Payload String payload) {

        var mapper = new ObjectMapper();

        try {
            var creditCardIssueData = mapper.readValue(payload, CreditCardIssueData.class);
            customerCardService.issueCreditCard(creditCardIssueData);
        
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InexistentCreditCardException e) {
            e.printStackTrace();
        }

    }
    
}

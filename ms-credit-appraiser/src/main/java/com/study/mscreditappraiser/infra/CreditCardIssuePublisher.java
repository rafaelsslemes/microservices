package com.study.mscreditappraiser.infra;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mscreditappraiser.domain.CreditCardIssueData;

@Component
public class CreditCardIssuePublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private Queue creditCardQueue;

    public void issueCreditCard(CreditCardIssueData issueData) throws JsonProcessingException {
        var json = convertToJson(issueData);
        rabbitTemplate.convertAndSend(creditCardQueue.getName(), json);
    }

    private String convertToJson(CreditCardIssueData issueData) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(issueData);

        return json;
    }

}

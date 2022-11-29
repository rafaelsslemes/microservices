package com.study.mscreditcard.infra;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CreditCardIssueSubscriber {

    @RabbitListener(queues = "${mq.queues.credit-card-issue}")
    public void receiveCreditCardIssue(@Payload String payload) {
        System.out.println(payload);
    }
    
}

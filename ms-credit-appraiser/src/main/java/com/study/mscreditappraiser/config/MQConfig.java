package com.study.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    
    @Value("${mq.queues.credit-card-issue}")
    private String queueName;

    public Queue creditCardIssueQueue(){
        return new Queue(queueName, true);
    }

}

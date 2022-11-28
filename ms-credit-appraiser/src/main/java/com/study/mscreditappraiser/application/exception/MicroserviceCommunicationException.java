package com.study.mscreditappraiser.application.exception;

import lombok.Getter;

public class MicroserviceCommunicationException extends Exception{

    @Getter
    private int status;

    public MicroserviceCommunicationException(int status, String message) {
        super(message);
        this.status = status;
    }

    
    
}

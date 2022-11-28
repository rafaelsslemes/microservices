package com.study.mscreditappraiser.application.exception;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException() {
        super("Customer not found to document informed");
    }
}

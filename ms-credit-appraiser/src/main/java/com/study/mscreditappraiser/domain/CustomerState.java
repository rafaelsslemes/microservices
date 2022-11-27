package com.study.mscreditappraiser.domain;

import java.util.List;

import lombok.Data;

@Data
public class CustomerState {

    private CustomerData customerData;
    private List<CustomerCard> cards;

}

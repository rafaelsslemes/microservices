package com.study.mscreditappraiser.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerState {

    private CustomerData customerData;
    private List<CustomerCard> cards;

}

package com.study.mscreditappraiser.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppraisalResult {

    private List<ApprovedCard> approvedCards;
    
}

package com.reactive.demo.demo.controller.dto;

import lombok.Data;

@Data
public class PersonInfo {
    private String
            firstName,
            lastName,
            middleName,
            errorMsg;
    private Boolean success;
    private RiskResult riskResult;
}

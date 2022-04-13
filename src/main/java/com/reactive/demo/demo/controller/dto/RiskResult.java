package com.reactive.demo.demo.controller.dto;

import lombok.Data;

@Data
public class RiskResult {
    private Boolean success;
    private String risk; /*ACCEPT/REJECT/VERIFICATION*/
}

package com.reactive.demo.demo.service.mapper;

import com.reactive.demo.demo.controller.dto.PersonInfo;
import com.reactive.demo.demo.controller.dto.RiskResult;
import org.springframework.stereotype.Component;

@Component
public class RiskMapper {

    public PersonInfo accept(){
        RiskResult risk = new RiskResult();
        risk.setRisk("ACCEPT");
        risk.setSuccess(true);

        PersonInfo res = new PersonInfo();
        res.setFirstName("May");
        res.setLastName("Sanejo");
        res.setMiddleName("Mei");
        res.setErrorMsg("none");
        res.setSuccess(true);
        res.setRiskResult(risk);

        return res;
    }
    public PersonInfo verification(){
        RiskResult risk = new RiskResult();
        risk.setRisk("VERIFICATION");
        risk.setSuccess(true);

        PersonInfo res = new PersonInfo();
        res.setFirstName("May");
        res.setLastName("Sanejo");
        res.setMiddleName("Mei");
        res.setErrorMsg("none");
        res.setSuccess(true);
        res.setRiskResult(risk);

        return res;
    }
    public PersonInfo reject(){
        RiskResult risk = new RiskResult();
        risk.setRisk("REJECT");
        risk.setSuccess(false);

        PersonInfo res = new PersonInfo();
        res.setFirstName("May");
        res.setLastName("Sanejo");
        res.setMiddleName("Mei");
        res.setErrorMsg("none");
        res.setSuccess(false);
        res.setRiskResult(risk);

        return res;
    }
}

package com.reactive.demo.demo.controller;

import com.reactive.demo.demo.controller.dto.PersonInfo;
import com.reactive.demo.demo.controller.dto.RiskResult;
import com.reactive.demo.demo.service.PersonService;
import com.reactive.demo.demo.service.mapper.RiskMapper;
import io.reactivex.rxjava3.core.Single;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private PersonService service;

    @MockBean
    private RiskMapper riskMapper;


    @Test
    public void accept() {
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

        Mockito.when(service.getPerson()).thenReturn(Single.just(res));

        webTestClient
                .get()
                .uri("/product/v1/getPerson")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonInfo.class)
                .value(r -> Assert.assertEquals("ACCEPT", r.getRiskResult().getRisk()));
    }

    @Test
    public void verify() {
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

        Mockito.when(service.getPerson()).thenReturn(Single.just(res));

        webTestClient
                .get()
                .uri("/product/v1/getPerson")
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PersonInfo.class)
                .value(r -> Assert.assertEquals("VERIFICATION", r.getRiskResult().getRisk()));
    }

    @Test
    public void reject() {
        RiskResult risk = new RiskResult();
        risk.setRisk("REJECT");
        risk.setSuccess(false);

        PersonInfo res = new PersonInfo();
        res.setFirstName("May");
        res.setLastName("Sanejo");
        res.setMiddleName("Mei");
        res.setErrorMsg("Success is false");
        res.setSuccess(false);
        res.setRiskResult(risk);

        Mockito.when(riskMapper.reject()).thenReturn(res);
        Mockito.when(service.getPerson()).thenReturn(Single.error(new Exception("Success is false")));

        webTestClient
                .get()
                .uri("/product/v1/getPerson")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(PersonInfo.class)
                .value(r -> Assert.assertEquals(r.getRiskResult().getRisk(), "REJECT"));
    }
}

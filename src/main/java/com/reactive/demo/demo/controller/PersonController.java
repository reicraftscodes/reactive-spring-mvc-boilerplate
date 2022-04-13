package com.reactive.demo.demo.controller;

import com.reactive.demo.demo.controller.dto.PersonInfo;
import com.reactive.demo.demo.service.PersonService;
import com.reactive.demo.demo.service.mapper.RiskMapper;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product/v1")
public class PersonController {

    @Autowired
    private PersonService service;
    @Autowired
    private RiskMapper mapper;

    @GetMapping("/getPerson")
    public Single<ResponseEntity<PersonInfo>> hello() {

        return service.getPerson()
                .flatMap(response-> {
                    /*RESULT IF NO ERROR -> TRUE*/

                    if(response.getRiskResult().getRisk().equals("VERIFICATION")){
                        log.info("VERIFICATION: "+response);
                        return Single.just(new ResponseEntity<>(response, HttpStatus.CREATED));
                    }
                    /*ACCEPT*/
                    log.info("ACCEPT: "+response);
                    return Single.just(new ResponseEntity<>(response, HttpStatus.OK));
                })
                .onErrorReturn(error -> {/*-> Triggered if service is return Single.error()*/

                    /*DEFAULT ERROR OR FALSE*/
                    log.error("ERROR: "+error.getMessage());
                    PersonInfo res = mapper.reject();

                    return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
                });
    }
}

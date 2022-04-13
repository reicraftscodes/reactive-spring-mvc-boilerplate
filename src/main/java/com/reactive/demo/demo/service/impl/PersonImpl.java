package com.reactive.demo.demo.service.impl;

import com.reactive.demo.demo.controller.dto.PersonInfo;
import com.reactive.demo.demo.service.PersonService;
import com.reactive.demo.demo.service.mapper.RiskMapper;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonImpl implements PersonService {

    @Autowired
    private RiskMapper mapper;

    @Override
    public Single<PersonInfo> getPerson(){

        PersonInfo res = mapper.reject();

        if(!res.getSuccess()){
            /*Trigger OnError from controller*/
            return Single.error(new Exception("Success is false"));
        }

        return Single.just(res); /*Trigger flatMap from controller*/
    }
}

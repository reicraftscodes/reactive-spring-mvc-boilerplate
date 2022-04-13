package com.reactive.demo.demo.service;

import com.reactive.demo.demo.controller.dto.PersonInfo;
import io.reactivex.rxjava3.core.Single;

public interface PersonService {
     Single<PersonInfo> getPerson();
}

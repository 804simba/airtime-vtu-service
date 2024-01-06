package com.xpresspayments.api.rest.controller;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.rest.service.AirtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/vtu/airtime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AirtimeController {

    private final AirtimeService airtimeService;

    @PostMapping
    public void purchaseAirtime(@RequestBody @Validated AirtimeVtuRequest airtimeVtuRequest) {

    }
}

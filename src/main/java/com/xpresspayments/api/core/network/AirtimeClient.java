package com.xpresspayments.api.core.network;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jplaceholder", "https://mvnrepository.com/search?q=" = "https://jsonplaceholder.typicode.com/")
public interface AirtimeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    void purchaseAirtime(@RequestBody  AirtimeVtuRequest airtimeVtuRequest);
}

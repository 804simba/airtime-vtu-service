package com.xpresspayments.api.core.network;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.model.dto.airtime.AirtimeVtuResponse;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "airtimeClient", url = "${xpresspayment.airtime.baseUrl}")
public interface BillerApiFeignClient {
    /*@RequestLine("POST /airtime/fulfill")
    @Headers({
            "Authorization: Bearer PUBLIC_KEY",
            "PaymentHash: GENERATED_HMAC",
            "Channel: API",
            "Content-Type: application/json"
    })*/
    @PostMapping("/airtime/fulfill")
    AirtimeVtuResponse purchaseAirtime(@RequestBody AirtimeVtuRequest airtimeVtuRequest);
}

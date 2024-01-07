//package com.xpresspayments.api.core.network;
//
//import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
//import com.xpresspayments.api.model.dto.airtime.AirtimeVtuResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "airtimeClient", url = "${xpresspayment.airtime.baseUrl}")
//public interface BillerApiFeignClient {
//    @PostMapping("/airtime/fulfill")
//    AirtimeVtuResponse purchaseAirtime(@RequestBody AirtimeVtuRequest airtimeVtuRequest);
//}

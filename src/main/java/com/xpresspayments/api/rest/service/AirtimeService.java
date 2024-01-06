package com.xpresspayments.api.rest.service;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.model.dto.base.BaseResponse;

public interface AirtimeService {
    BaseResponse<?> purchaseAirtime(AirtimeVtuRequest airtimeVtuRequest);
}

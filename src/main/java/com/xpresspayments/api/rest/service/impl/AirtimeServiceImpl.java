package com.xpresspayments.api.rest.service.impl;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.rest.service.AirtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirtimeServiceImpl implements AirtimeService {
    @Override
    public BaseResponse<?> purchaseAirtime(AirtimeVtuRequest airtimeVtuRequest) {
        return null;
    }
}

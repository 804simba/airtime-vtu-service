package com.xpresspayments.api.rest.service.impl;

import com.xpresspayments.api.core.builders.VtuAirtimeTransactionBuilder;
import com.xpresspayments.api.core.exception.AirtimePurchaseException;
import com.xpresspayments.api.core.exception.GenericException;
import com.xpresspayments.api.core.exception.InvalidRequestException;
import com.xpresspayments.api.core.network.BillerApiFeignClient;
import com.xpresspayments.api.core.utils.BillerTransactionIdGenerator;
import com.xpresspayments.api.core.utils.Constants;
import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.model.dto.airtime.AirtimeVtuResponse;
import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.model.dto.user.PurchaseAirtimeRequest;
import com.xpresspayments.api.model.entity.TelecomNetworkProvider;
import com.xpresspayments.api.model.entity.User;
import com.xpresspayments.api.model.entity.VtuAirtimeTransaction;
import com.xpresspayments.api.model.enums.TransactionStatus;
import com.xpresspayments.api.model.repository.TelecomNetworkProviderRepository;
import com.xpresspayments.api.model.repository.UserRepository;
import com.xpresspayments.api.model.repository.VtuAirtimeTransactionRepository;
import com.xpresspayments.api.rest.service.AirtimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirtimeServiceImpl implements AirtimeService {

    private final BillerApiFeignClient billerApiFeignClient;

    private final UserRepository userRepository;

    private final TelecomNetworkProviderRepository telecomNetworkProviderRepository;

    private final VtuAirtimeTransactionRepository vtuAirtimeTransactionRepository;

    @Override
    public BaseResponse<?> purchaseAirtime(PurchaseAirtimeRequest purchaseAirtimeRequest, Principal principal) {
        BaseResponse<?> br = null;
       try {
           Optional<User> foundUser = userRepository.findUserByContact_EmailAddress(principal.getName());
           if (foundUser.isPresent()) {
               AirtimeVtuRequest airtimeVtuRequest = new AirtimeVtuRequest();
               airtimeVtuRequest.setRequestId(BillerTransactionIdGenerator.generateTransactionId());
               TelecomNetworkProvider telecomNetworkProvider = telecomNetworkProviderRepository.findTelecomNetworkProviderByProviderNameEqualsIgnoreCase(purchaseAirtimeRequest.getProvider());
              if (!ObjectUtils.isEmpty(telecomNetworkProvider)) {
                  String uniqueCode = telecomNetworkProvider.getUniqueCode();
                  if (!ObjectUtils.isEmpty(uniqueCode)) {
                      airtimeVtuRequest.setUniqueCode(uniqueCode);
                      AirtimeVtuRequest.Details details = new AirtimeVtuRequest.Details();
                      details.setAmount(purchaseAirtimeRequest.getAmount());
                      details.setPhoneNumber(purchaseAirtimeRequest.getPhoneNumber());
                      airtimeVtuRequest.setDetails(details);
                      AirtimeVtuResponse airtimeVtuResponse = billerApiFeignClient.purchaseAirtime(airtimeVtuRequest);

                      VtuAirtimeTransaction vtuAirtimeTransaction = VtuAirtimeTransactionBuilder.mapResponseToVtuAirtimeTransaction(airtimeVtuRequest, foundUser.get());
                      if (!ObjectUtils.isEmpty(airtimeVtuResponse)){
                          if (airtimeVtuResponse.getResponseCode().equals(Constants.BILLER_SUCCESS_CODE) &&
                                  airtimeVtuResponse.getResponseMessage().equals(Constants.BILLER_SUCCESS_MESSAGE)) {
                              vtuAirtimeTransaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                          } else {
                              vtuAirtimeTransaction.setTransactionStatus(TransactionStatus.FAILED);
                          }
                          vtuAirtimeTransactionRepository.save(vtuAirtimeTransaction);
                          br = BaseResponse.builder().responseCode(HttpStatus.OK.value()).responseMessage("airtime purchase successful").payload(vtuAirtimeTransaction).build();
                      } else {
                          throw new GenericException("invalid response from biller airtime service");
                      }
                  } else {
                      throw new GenericException("unique code does not exist for provider");
                  }
              } else {
                  throw new InvalidRequestException("invalid network provider for airtime transaction");
              }
           } else {
               throw new UsernameNotFoundException("user does not exist");
           }
       } catch (Exception e) {
            log.info("[ AIRTIME-SERVICE ] -- error: {}", e.getMessage());
            throw new AirtimePurchaseException("error purchasing airtime: {}", e);
       }
        return br;
    }
}

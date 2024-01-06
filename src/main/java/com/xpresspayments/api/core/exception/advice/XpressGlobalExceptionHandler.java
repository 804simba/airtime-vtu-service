package com.xpresspayments.api.core.exception.advice;

import com.xpresspayments.api.core.exception.InvalidCredentialsException;
import com.xpresspayments.api.model.dto.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class XpressGlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public BaseResponse<?> invalidCredentialsException(InvalidCredentialsException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }
}

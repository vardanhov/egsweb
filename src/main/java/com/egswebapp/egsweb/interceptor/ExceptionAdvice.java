package com.egswebapp.egsweb.interceptor;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.excpetions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {



    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleFields(ServiceException ex) {
        if (ex.getErrorResponses()!=null) {
            return new ResponseEntity<>(ex.getErrorResponses(), ex.getStatus());
        } else {
            ErrorResponse response = new ErrorResponse();
            response.setMessage(ex.getMessage());
            response.setFields(ex.getStatus().name());
            return new ResponseEntity<>(response, ex.getStatus());
        }


    }




}

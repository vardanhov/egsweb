package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class ServiceException extends RuntimeException {

    private HttpStatus status;

    private String message;

    private int errorCode;

    private List<ErrorResponse> errorResponses;

    protected ServiceException() {


    }

    public ServiceException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.getStatus().value();
        this.errorResponses = null;
    }

    public ServiceException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.getStatus().value();
        this.errorResponses = errorResponses;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ErrorResponse> getErrorResponses() {
        return errorResponses;
    }

    public void setErrorResponses(List<ErrorResponse> errorResponses) {
        this.errorResponses = errorResponses;
    }
}

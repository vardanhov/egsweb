package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class JwtAuthenticationException extends ServiceException {

    public JwtAuthenticationException() {
    }

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtAuthenticationException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}

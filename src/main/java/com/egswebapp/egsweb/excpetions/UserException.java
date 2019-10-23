package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;
import java.util.function.Supplier;

public class UserException extends  ServiceException{

    public UserException() {
    }

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}

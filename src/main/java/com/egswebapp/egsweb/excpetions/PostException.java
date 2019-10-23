package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class PostException extends ServiceException

{
    public PostException() {
    }

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}

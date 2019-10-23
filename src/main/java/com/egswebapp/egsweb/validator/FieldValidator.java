package com.egswebapp.egsweb.validator;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.AuthErrorCode;
import com.egswebapp.egsweb.excpetions.AuthExceptions;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class FieldValidator {

    private FieldValidator() {
    }

    public void validateBodyFields(final BindingResult result) {
        if (result.hasErrors()) {
            final List<ErrorResponse> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(new ErrorResponse(((FieldError) error).getField(), error.getDefaultMessage())));

            throw new AuthExceptions(AuthErrorCode.AUTH_INVALID_CREDENTIALS, errors);
        }



    }


}

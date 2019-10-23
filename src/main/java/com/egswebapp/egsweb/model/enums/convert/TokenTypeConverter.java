package com.egswebapp.egsweb.model.enums.convert;

import com.egswebapp.egsweb.model.enums.TokenType;

import javax.persistence.AttributeConverter;

public class TokenTypeConverter implements AttributeConverter<TokenType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TokenType value) {
        if (value == null) {
            return null;
        }

        return value.getValue();
    }

    @Override
    public TokenType convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return TokenType.ofValue(value);

    }
}


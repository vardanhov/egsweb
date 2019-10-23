package com.egswebapp.egsweb.model.enums.convert;

import com.egswebapp.egsweb.model.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert

public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus value) {
        if (value == null) {
            return null;
        }
        return value.getValue();
    }


    @Override
    public UserStatus convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return UserStatus.ofValue(value);
    }
}

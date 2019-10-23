package com.egswebapp.egsweb.model.enums.convert;

import com.egswebapp.egsweb.model.enums.UserProfile;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class UserProfileConverter implements AttributeConverter<UserProfile, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserProfile value) {
        if (value == null) {
            return null;
        }

        return value.getValue();
    }

    @Override
    public UserProfile convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return UserProfile.ofValue(value);

    }
}

package com.egswebapp.egsweb.model.enums.convert;

import com.egswebapp.egsweb.model.enums.Language;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class LanguagesConverter implements AttributeConverter<Language, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Language value) {
        if (value == null) {
            return null;
        }
        return value.getValue();
    }

    @Override
    public Language convertToEntityAttribute(Integer value) {
        if (value==null){
            return  null;
        }

        return Language.forValue(value);
    }
}

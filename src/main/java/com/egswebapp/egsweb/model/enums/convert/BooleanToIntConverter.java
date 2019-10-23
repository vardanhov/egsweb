package com.egswebapp.egsweb.model.enums.convert;



import javax.persistence.AttributeConverter;

public class BooleanToIntConverter implements AttributeConverter<Boolean, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Boolean value)
    {
        return value == true ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer value) {

        if (value == null) {
            return null;
        }
        if (value.equals(1)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
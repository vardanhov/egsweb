package com.egswebapp.egsweb.util;

import org.springframework.stereotype.Component;

@Component
public class NumberFormatCheck {


    public boolean isNumeric(final String number) {
        if (number == null || number.length() == 0) {
            return false;
        }
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}

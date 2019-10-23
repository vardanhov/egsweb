package com.egswebapp.egsweb.util;

import org.springframework.stereotype.Component;

@Component
public class RoleConverter {


    private RoleConverter() {

    }


    public String roleConverter(final String userProfile) {
        String userProfileName = null;
        if (userProfile.equalsIgnoreCase("role_admin")) {
            userProfileName = "ROLE_ADMIN";
        }
        if (userProfile.equalsIgnoreCase("role_user")) {
            userProfileName = "ROLE_USER";
        }
        return userProfileName;
    }


}

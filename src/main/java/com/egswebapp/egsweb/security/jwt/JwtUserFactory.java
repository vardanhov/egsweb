package com.egswebapp.egsweb.security.jwt;

import com.egswebapp.egsweb.model.User;
import com.egswebapp.egsweb.model.enums.UserProfile;
import com.egswebapp.egsweb.model.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of Factory Method for class {@link JwtUserDetails}.
 *
 *
 * @version 1.0
 */


public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUserDetails create(final User user) {
        return new JwtUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getStatus().equals(UserStatus.ACTIVE),
                mapToGrantedAuthority(new ArrayList<>(Collections.singleton(user.getUserProfile())))
                );
    }


    private static List<GrantedAuthority> mapToGrantedAuthority(List<UserProfile> userProfiles) {
        return userProfiles.stream()
                .map(profile ->
                        new SimpleGrantedAuthority(profile.getName())
                ).collect(Collectors.toList());
    }

}

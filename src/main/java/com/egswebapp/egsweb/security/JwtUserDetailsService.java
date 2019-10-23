package com.egswebapp.egsweb.security;

import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.User;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public JwtUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user   =repository.findUserByEmail(email);
        if (user==null){
            throw  new UserException(UserErrorCode.USER_NOT_FOUND);
        }


        return  JwtUserFactory.create(user);
    }
}

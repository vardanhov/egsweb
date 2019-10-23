package com.egswebapp.egsweb.service.impl;

import com.egswebapp.egsweb.dto.request.UserLoginDto;
import com.egswebapp.egsweb.dto.response.UserResponseDto;
import com.egswebapp.egsweb.enums.AuthErrorCode;
import com.egswebapp.egsweb.excpetions.AuthExceptions;
import com.egswebapp.egsweb.model.User;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private UserRepository repository;

    private MapperFacade mapper;


    private BCryptPasswordEncoder encoder;


    @Autowired
    public AuthServiceImpl(UserRepository repository, MapperFacade mapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public UserResponseDto login(final UserLoginDto loginDto ) {
        log.info("trying to login");


        final User user = repository.findUserByEmail(loginDto.getEmail());
        if (user == null) {
            log.info("user by username dont exist");
            throw new AuthExceptions(AuthErrorCode.USER_DONT_EXIST);
        }
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new AuthExceptions(AuthErrorCode.USER_DONT_EXIST);
        }
        if (!user.getStatus().getName().equalsIgnoreCase("active")) {
            throw new AuthExceptions(AuthErrorCode.USER_PROFILE_NOT_ACTIVE);
        }

        return mapper.map(user, UserResponseDto.class);
    }



}

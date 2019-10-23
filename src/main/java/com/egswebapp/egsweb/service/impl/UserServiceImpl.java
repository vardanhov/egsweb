package com.egswebapp.egsweb.service.impl;

import com.egswebapp.egsweb.dto.request.UserPasswordDto;
import com.egswebapp.egsweb.dto.request.UserRequestDto;
import com.egswebapp.egsweb.dto.request.UserUpdateDto;
import com.egswebapp.egsweb.dto.response.UserResponseDto;
import com.egswebapp.egsweb.enums.AuthErrorCode;
import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.AuthExceptions;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.User;
//import com.egswebapp.egsweb.model.UserToken;
import com.egswebapp.egsweb.model.enums.UserProfile;
import com.egswebapp.egsweb.model.enums.UserStatus;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.UserService;
import com.egswebapp.egsweb.util.*;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final MapperFacade mapper;

    private final BCryptPasswordEncoder encoder;

    private final EmailSendUtil emailSender;

    private final CreateMessage createMassage;

    private final FileUtil fileUtil;


    @Autowired
    public UserServiceImpl(FileUtil fileUtil, UserRepository userRepository, MapperFacade mapper, BCryptPasswordEncoder encoder, CreateMessage createMassage) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.emailSender = EmailSendUtil.getInstance();
        this.createMassage = createMassage;
        this.fileUtil = fileUtil;
    }

    @Override
    @Transactional
    public void save(final UserRequestDto requestDto, final JwtUserDetails userDetails) {
        log.info("tyring to save user");
        if (userRepository.existsUserByEmail(requestDto.getEmail())) {
            throw new AuthExceptions(AuthErrorCode.EMAIL_INVALID_CREDENTIALS);
        }
        final User user = mapper.map(requestDto, User.class);
        user.setUserProfile(UserProfile.ROLE_USER);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        if (userDetails != null) {
            createUserByAdmin(user);
            final String subject = "Registration user";
            final String text = "dear" + user.getName() + " " + user.getSurname() + " you are registered and your username and password can find below to sign IN " +
                    user.getEmail() + " " + user.getPassword() + " you can change your password";
            final Email email = createMassage.createMassage(user.getEmail(), subject, text);
            emailSender.sendEmail(user, email);
        } else {
            createUserByUser(user);
        }

        userRepository.save(user);
    }


    private void createUserByUser(final User user) {
        user.setStatus(UserStatus.INACTIVE);
    }

    private void createUserByAdmin(final User user) {
        user.setStatus(UserStatus.ACTIVE);
    }

    @Override
    public User findUserByEmail(final String email) {
        final User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        return user;
    }


    @Override
    @Transactional
    public void updateUserPassword(final UserPasswordDto dto, final JwtUserDetails userDetails) {
        if (!userRepository.existsUserById(userDetails.getId())) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        User user = userRepository.findUserById(userDetails.getId());
        if (!encoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.NOT_SAME_PASSWORD);
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new UserException(UserErrorCode.NOT_SAME_PASSWORD);
        }

        if (encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.USER_OLD_PASSWORD);
        }

        user.setPassword(encoder.encode(dto.getPassword()));
        userRepository.save(user);

        log.info("password  successfully changed");

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(String id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return mapper.map(user, UserResponseDto.class);

    }

    @Override
    @Transactional
    public void updateUserData(final UserUpdateDto update, final String id) {

        final User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        user.setSurname(update.getSurname());
        user.setName(update.getName());
        userRepository.save(user);
        log.info("user data change is success");
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUser() {
        List<User> allUser = userRepository.findAll();
        List<UserResponseDto> dto = mapper.mapAsList(allUser, UserResponseDto.class);
        return dto;
    }

    @Override
    @Transactional
    public void addFile(final String id, final MultipartFile file) {
        log.info("trying to uploaded pictures");
        final User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        fileUtil.deleteFile(user.getImgName(), Constant.USER_PACKAGE);
        final String fileName = fileUtil.getFileName(file, Constant.USER_PACKAGE);
        user.setImgName(fileName);
        userRepository.save(user);
        log.info("pictures upload has been success");
    }

    @Override
    public void delete(String id) {
        log.info("trying to delete pictures");
        final User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        fileUtil.deleteFile(user.getImgName(), Constant.USER_PACKAGE);
        user.setImgName(null);
        userRepository.save(user);
        log.info("delete pictures has been success");
    }

    @Override
    @Transactional
    public void changePassword(final String mail) {
        final User user = userRepository.findUserByEmail(mail);
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        final String newPassword = PasswordGenerator.generateRandomPassword(10);
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        final String subject = "Change password";
        final String text = "Your password has changed. Your login and password is " +
                user.getEmail() + " " + user.getPassword();
        final Email email = createMassage.createMassage(user.getEmail(), subject, text);

        emailSender.sendEmail(user, email);
    }
}

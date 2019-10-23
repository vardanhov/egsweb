package com.egswebapp.egsweb.service.impl;


import com.egswebapp.egsweb.dto.request.UserUpdateRoleDto;
import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.User;
import com.egswebapp.egsweb.model.enums.UserProfile;
import com.egswebapp.egsweb.model.enums.UserStatus;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.service.AdminService;
import com.egswebapp.egsweb.util.CreateMessage;
import com.egswebapp.egsweb.util.Email;
import com.egswebapp.egsweb.util.EmailSendUtil;
import com.egswebapp.egsweb.util.RoleConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PreAuthorize("hasRole('Admin')")
@Slf4j
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private RoleConverter converter;
    private EmailSendUtil emailSender;
    private CreateMessage createMassage;


    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleConverter converter, CreateMessage createMassage) {
        this.userRepository = userRepository;
        this.converter = converter;
        emailSender = EmailSendUtil.getInstance();
        this.createMassage = createMassage;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void roleUpdate(final UserUpdateRoleDto updateDto) {
        final User user = userRepository.findUserById(updateDto.getUserId());
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        final UserProfile profile = UserProfile.ofName(converter.roleConverter(updateDto.getRole()));
        if (user.getUserProfile() == profile) {
            throw new UserException(UserErrorCode.EDIT_USER_ROLE);
        }
        user.setUserProfile(UserProfile.ofName(converter.roleConverter(updateDto.getRole())));
        userRepository.save(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('Admin')")
    public void activateUser(final String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        if (!UserStatus.INACTIVE.equals(user.getStatus())) {
            throw new UserException(UserErrorCode.USER_PROFILE_IS_ACTIVATED);
        }
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        final String subject = "Activate User";
        final String text = "dear " + user.getName() + " " + user.getSurname() + " your account is active, you can sign in";
        final Email email = createMassage.createMassage(user.getEmail(), subject, text);
        emailSender.sendEmail(user, email);
        log.info("user created is success");

    }




}

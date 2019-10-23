package com.egswebapp.egsweb.controller;

import com.egswebapp.egsweb.dto.request.UserLoginDto;
import com.egswebapp.egsweb.dto.request.UserPasswordDto;
import com.egswebapp.egsweb.dto.request.UserRequestDto;
import com.egswebapp.egsweb.dto.request.UserUpdateDto;
import com.egswebapp.egsweb.dto.response.ApiResponse;
import com.egswebapp.egsweb.dto.response.SuccessResponse;
import com.egswebapp.egsweb.dto.response.UserResponseDto;
import com.egswebapp.egsweb.enums.AuthErrorCode;
import com.egswebapp.egsweb.excpetions.AuthExceptions;
import com.egswebapp.egsweb.security.CurrentUser;
import com.egswebapp.egsweb.security.jwt.JwtTokenProvider;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.AuthService;
import com.egswebapp.egsweb.service.UserService;
import com.egswebapp.egsweb.util.PasswordGenerator;
import com.egswebapp.egsweb.validator.FieldValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/users")
@Slf4j
@Api(value = "UserController Resource, functionality for user ", description = "shows user functionality")
public class UserController {


    private UserService userService;

    private FieldValidator validator;

    private JwtTokenProvider provider;

    private AuthService authService;

    private final AuthenticationManager authentication;


    @Autowired
    public UserController(UserService userService, FieldValidator validator, JwtTokenProvider provider, AuthService authService, AuthenticationManager authentication) {
        this.userService = userService;
        this.validator = validator;
        this.provider = provider;
        this.authService = authService;
        this.authentication = authentication;
    }


    /**
     * save user
     *
     * @param dto         Map{@link UserRequestDto} to {@link com.egswebapp.egsweb.model.User}
     * @param result      checked if registration field is not empty
     * @param currentUser it shows that have an admin in the login
     */
    @ApiOperation(value = "for registration, request registration data, " +
            "the registration field bust not be empty hen user creates an account. " +
            "When an admin creates an account the password filed must be hidden, " +
            "and send the default value. " +
            "There are   three parameters, the first parameter dto(request data), " +
            "the second parameter result(checked have an error in the registration fields), " +
            "and the last parameter shows that have a sign in admin, " +
            "to create the user  if currentUser== null, " +
            "it shows that does not have an admin in the login ")
    @PostMapping(path = "/register")
    public ResponseEntity<SuccessResponse> save(@RequestBody @Valid final UserRequestDto dto, final BindingResult result, @CurrentUser JwtUserDetails currentUser) {
        log.info("trying to check register fields");
        if (currentUser != null) {
            final String generatorPassword = PasswordGenerator.generateRandomPassword(10);
            dto.setPassword(generatorPassword);
            dto.setConfirmPassword(generatorPassword);
        }
        validator.validateBodyFields(result);
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            log.info("have errors in the register field");
            throw new AuthExceptions(AuthErrorCode.CHECK_CONFIRM_PASSWORD);
        }
        userService.save(dto, currentUser);
        return ResponseEntity.ok(new SuccessResponse(true));
    }


    /**
     * login
     *
     * @param dto    Map{@link UserLoginDto} to {@link com.egswebapp.egsweb.model.User}
     * @param result checked if login field is not empty
     */
    @PostMapping(path = "/login")
    @ApiOperation(value = "form login, write correct email and password")
    public ResponseEntity<SuccessResponse> login(@RequestBody @Valid final UserLoginDto dto, final BindingResult result) {
        log.info("trying to login");
        validator.validateBodyFields(result);
        final String email = dto.getEmail();
        final UserResponseDto login = authService.login(dto);
        authentication.authenticate(new UsernamePasswordAuthenticationToken(email, dto.getPassword()));
        String token = provider.creatTokent(email, login.getUserProfile());
        Map<Object, Object> response = new HashMap<>();
        response.put("dto", login);
        response.put("token", token);
        return ResponseEntity.ok(new SuccessResponse<>(true, response));


    }

    /**
     * update password
     *
     * @param dto         Map{@link UserPasswordDto} to {@link com.egswebapp.egsweb.model.User}
     * @param result      checked if update passwords fields is not empty
     * @param currentUser it shows that have an admin otr user in the login
     */
    @ApiOperation(value = "change password after login: user or admin can change password:")
    @PatchMapping(path = "/update_password")
    @ResponseBody
    public ResponseEntity<SuccessResponse> updatePassword(@RequestBody @Valid final UserPasswordDto dto, final BindingResult result, @CurrentUser JwtUserDetails currentUser) {
        log.info("trying to change user password");
        validator.validateBodyFields(result);
        userService.updateUserPassword(dto, currentUser);
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @ApiOperation(value = "get user data ")
    @GetMapping("/update_user_data/{id}")
    public UserResponseDto getUserDate(@PathVariable(name = "id") final String id) {
        return userService.findUserById(id);
    }


    /**
     * update user data
     *
     * @param dto    Map{@link UserUpdateDto} to {@link com.egswebapp.egsweb.model.User}
     * @param result checked if update users fields is not empty
     * @param id     user id
     */
    @ApiOperation(value = "update user data by id  @param dto Map{@link UserUpdateDto} to {@link com.egswebapp.egsweb.model.User}" +
            "@param result checked if update users fields is not empty" +
            "@param  id   user id")
    @PutMapping("/update_user_data/{id}")
    @ResponseBody
    public ResponseEntity<SuccessResponse> updateUserData(@Valid @RequestBody final UserUpdateDto dto, final BindingResult result, @PathVariable(name = "id") String id) {
        log.info("trying to update user data");
        validator.validateBodyFields(result);
        userService.updateUserData(dto, id);
        return ResponseEntity.ok(new SuccessResponse(true));
    }


    /**
     * get all user
     */
    @ApiOperation(value = "get_all_user")
    @GetMapping("/all_user")
    @ResponseBody
    public List<UserResponseDto> getAllUser() {
        return userService.getAllUser();
    }


    @PostMapping("/upload_pictures")
    @ApiOperation(value = "upload user avatar pictures")
    public ResponseEntity<SuccessResponse> fileUpload(@CurrentUser JwtUserDetails currentUser, @RequestParam(name = "file") final MultipartFile file) {
        userService.addFile(currentUser.getId(), file);
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @PostMapping("/delete_pictures")
    public ResponseEntity<SuccessResponse> deleteFile(@CurrentUser final JwtUserDetails userDetails) {
        userService.delete(userDetails.getId());
        return ResponseEntity.ok(new SuccessResponse(true));
    }


    @POST
    @Path("/change-password")
    @ApiOperation(value = "Send email new password")
    public ResponseEntity<ApiResponse> changePassword(@Valid @QueryParam("email") String email) {
        log.info("trying to change user password");
        userService.changePassword(email);

        return new ResponseEntity<>(new ApiResponse(true, "Password has changed success"), HttpStatus.OK);
    }

}

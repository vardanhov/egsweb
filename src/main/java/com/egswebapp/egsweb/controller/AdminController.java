package com.egswebapp.egsweb.controller;

import com.egswebapp.egsweb.dto.request.UserUpdateRoleDto;
import com.egswebapp.egsweb.dto.response.SuccessResponse;
import com.egswebapp.egsweb.service.AdminService;
import com.egswebapp.egsweb.validator.FieldValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/admin")
@Slf4j
@Api(value = "AdminController Resource, functionality for Admin ", description = "shows Admin functionality")
public class AdminController {

    private AdminService adminService;

    private FieldValidator validator;

    @Autowired
    public AdminController(AdminService adminService, FieldValidator validator) {
        this.adminService = adminService;
        this.validator = validator;
    }

    /**
     * activate user
     *
     * @param id the {@Link id} id of user
     */
    @PutMapping(path = "/activateUser/{id}")
    @ApiOperation(value = "activate user")
    public ResponseEntity<SuccessResponse> activateUser(@PathVariable(name = "id") final String id) {
        log.info("trying to activate user");
        adminService.activateUser(id);
        return ResponseEntity.ok(new SuccessResponse(true));
    }


    /**
     * update user role type by admin
     * Admin can update user role, give him  admin permission or user permission
     *
     * @param dto    the {@link UserUpdateRoleDto} instance to user
     * @param result the{@link BindingResult}
     */
    @ApiOperation(value = "Admin can update user role, give him  admin permission or user permission")
    @PutMapping(value = "/update_role")
    public ResponseEntity<SuccessResponse> updateUserRole(@RequestBody @Valid final UserUpdateRoleDto dto, final BindingResult result) {
        log.info("trying to update user role type");
        validator.validateBodyFields(result);
        adminService.roleUpdate(dto);
        return ResponseEntity.ok(new SuccessResponse(true));
    }


}


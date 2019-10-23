package com.egswebapp.egsweb.controller;

import com.egswebapp.egsweb.dto.request.CategoryRequestDto;
import com.egswebapp.egsweb.dto.response.SuccessResponse;
import com.egswebapp.egsweb.enums.CategoryErrorCode;
import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.CategoryException;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.Category;
import com.egswebapp.egsweb.security.CurrentUser;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.CategoryService;
import com.egswebapp.egsweb.util.NumberFormatCheck;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/category")
@Slf4j
@Api(value = "CategoryController Resource, functionality for user ", description = "shows user functionality")
public class CategoryController {

    private CategoryService service;

    private FieldValidator validator;

    private final NumberFormatCheck checkNumber;

    @Autowired
    public CategoryController(CategoryService service, FieldValidator validator, NumberFormatCheck checkNumber) {
        this.service = service;
        this.validator = validator;
        this.checkNumber = checkNumber;
    }


    @PostMapping(value = "create_category")
    @ResponseBody
    @ApiOperation(value = "save category")
    public ResponseEntity<SuccessResponse> save(@RequestBody @Valid final CategoryRequestDto dto, final BindingResult result, @CurrentUser final JwtUserDetails currentUser) {
        if (currentUser == null) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        validator.validateBodyFields(result);
        service.save(dto);
        return ResponseEntity.ok(new SuccessResponse<>(true, dto.getName()));
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    @ApiOperation(value = "delete category by id")
    public ResponseEntity<SuccessResponse> delete(@PathVariable(name = "id") final Long id) {
        if (!checkNumber.isNumeric(id.toString())) {
            throw new CategoryException(CategoryErrorCode.NUMBER_FORMAT_IS_NOT_VALID);
        }
        service.delete(id);
        return ResponseEntity.ok(new SuccessResponse(true));

    }

    @PutMapping("/rename/{id}")
    @ResponseBody
    @ApiOperation(value = "rename category by id")
    public ResponseEntity<SuccessResponse> rename(@RequestBody @Valid final CategoryRequestDto dto, final BindingResult result, @PathVariable(name = "id") final Long id) {
        log.info("trying to check filed");
        if (!checkNumber.isNumeric(id.toString())) {
            throw new CategoryException(CategoryErrorCode.NUMBER_FORMAT_IS_NOT_VALID);
        }
        validator.validateBodyFields(result);
        service.rename(id, dto);
        log.info("name is changed successfully");
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @GetMapping("category_list")
    public List<Category> getAllCategory() {
        return service.getAllCategory();
    }

}

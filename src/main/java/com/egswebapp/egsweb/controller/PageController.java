package com.egswebapp.egsweb.controller;

import com.egswebapp.egsweb.dto.request.PageDeleteRequestDto;
import com.egswebapp.egsweb.dto.request.PageRequestDto;
import com.egswebapp.egsweb.dto.response.PageResponseDto;
import com.egswebapp.egsweb.dto.response.SuccessResponse;
import com.egswebapp.egsweb.security.CurrentUser;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.PageService;
import com.egswebapp.egsweb.validator.FieldValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/pages")
@Slf4j
@Api(value = "PageController Resource, functionality for page ", description = "shows page functionality")
public class PageController {

    private final PageService pageService;
    private final FieldValidator validator;

    @Autowired
    public PageController(PageService pageService, FieldValidator validator) {
        this.pageService = pageService;
        this.validator = validator;
    }


    @PostMapping(value = "/create_page", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "save page")
    public ResponseEntity<SuccessResponse> save(@Valid @ModelAttribute final PageRequestDto dto,
                                                final BindingResult bindingResult,
                                                @CurrentUser JwtUserDetails users,
                                                @RequestParam("file") final List<MultipartFile> file) {

        validator.validateBodyFields(bindingResult);
        log.info("trying to save page");
        pageService.save(dto, users, file);
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @GetMapping(value = "/all_page")
    @ApiOperation(value = "get all page")
    public List<PageResponseDto> getAllPage() {
        log.info("get all page");
        return pageService.getAllPage();
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "update page ")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") final String id,
                                                  @Valid @RequestBody PageRequestDto dto,
                                                  @CurrentUser JwtUserDetails currentUser) {
        log.info("trying to update page");
        pageService.update(id, dto, currentUser);
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "delete page ")
    public ResponseEntity<SuccessResponse> delete(@Valid @RequestBody final PageDeleteRequestDto dto,
                                                  final BindingResult result) {
        log.info("trying to delete");
        validator.validateBodyFields(result);
        pageService.delete(dto);
        return ResponseEntity.ok(new SuccessResponse(true));
    }


}

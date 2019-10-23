package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.PageDeleteRequestDto;
import com.egswebapp.egsweb.dto.request.PageRequestDto;
import com.egswebapp.egsweb.dto.response.PageResponseDto;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PageService {

    /**
     * save page
     *
     * @param requestDto
     * @param users
     * @param files
     */
    void save(final PageRequestDto requestDto, final JwtUserDetails users, final List<MultipartFile> files);

    /**
     * read all page
     */
    List<PageResponseDto> getAllPage();

    /**
     * update pages
     *
     * @param id
     * @param requestDto
     * @param currentUser
     */
    void update(final String id, final PageRequestDto requestDto, final JwtUserDetails currentUser);


    /**
     * delete page
     *
     * @param dto
     */
    void delete(final PageDeleteRequestDto dto);


}

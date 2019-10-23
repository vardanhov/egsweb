package com.egswebapp.egsweb.service.impl;

import com.egswebapp.egsweb.dto.request.PageDeleteRequestDto;
import com.egswebapp.egsweb.dto.request.PageRequestDto;
import com.egswebapp.egsweb.dto.response.PageResponseDto;
import com.egswebapp.egsweb.enums.PageErrorCode;
import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.PageException;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.*;
import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.repasotory.PageContentRepository;
import com.egswebapp.egsweb.repasotory.PageRepository;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.PageService;
import com.egswebapp.egsweb.util.Constant;
import com.egswebapp.egsweb.util.FileUtil;
import com.egswebapp.egsweb.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    private final UserRepository userRepository;

    private final PageContentRepository pageContentRepository;

    private final FileUtil fileUtil;

    private final MapperFacade mapper;


    @Autowired
    public PageServiceImpl(PageRepository pageRepository, UserRepository userRepository, PageContentRepository pageContentRepository, FileUtil fileUtil, MapperFacade mapper) {
        this.pageRepository = pageRepository;
        this.pageContentRepository = pageContentRepository;
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
        this.mapper = mapper;
    }


    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void save(final PageRequestDto requestDto, final JwtUserDetails currentUser, final List<MultipartFile> files) {
        final User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Page page = new Page();
        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(fileUtil.getFileName(file, Constant.PAGE_PACKAGE)));
        page.setImgName(fileUtil.getOriginImagesName(String.valueOf(fileNames)));
        log.info("save images in folder");

        PageContentId pageContentId = new PageContentId();
        pageContentId.setLanguage(Language.forName(requestDto.getLanguages()));

        PageContent pageContent = mapper.map(requestDto, PageContent.class);
        pageContent.setUser(user);
        pageContent.setPage(page);
        pageContent.setPageContentId(pageContentId);
        pageRepository.save(page);
        pageContentRepository.save(pageContent);
        log.info("page save is success");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PageResponseDto> getAllPage() {
        List<PageContent> pageContents = pageContentRepository.findAll();
        return MapUtils.mapAsList(pageContents);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(final String id, final PageRequestDto requestDto, final JwtUserDetails currentUser) {
        final User user = userRepository.findById(currentUser.getId()).
                orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        log.info("find user by id");
        PageContentId contentId = new PageContentId();
        contentId.setLanguage(Language.forName(requestDto.getLanguages()));
        contentId.setPageId(id);
        final PageContent content = pageContentRepository.findById(contentId).orElseThrow(() ->
                new PageException(PageErrorCode.PAGE_NOT_FOUND));
        log.info("find pagContent by PageContentId");
        content.setDescription(requestDto.getDescription());
        content.setTitle(requestDto.getTitle());
        content.setUser(user);
        pageContentRepository.save(content);
        log.info("page has been updating");
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(final PageDeleteRequestDto dto) {
        Page page = pageRepository.findById(dto.getId()).
                orElseThrow(() -> new PageException(PageErrorCode.PAGE_NOT_FOUND));
        log.info("find page by id");
        String[] imagesName = page.getImgName().split(",");
        for (String imgName : imagesName) {
            log.info("trying delete images from folder");
            fileUtil.deleteFile(imgName.trim(),Constant.PAGE_PACKAGE);
        }
        PageContentId contentId = new PageContentId();
        contentId.setPageId(page.getId());
        contentId.setLanguage(Language.forName(dto.getLanguages()));
        pageContentRepository.deleteById(contentId);
        log.info("pages has been deleted");
    }


}

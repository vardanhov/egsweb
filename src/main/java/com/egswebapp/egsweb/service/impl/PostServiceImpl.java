package com.egswebapp.egsweb.service.impl;


import com.egswebapp.egsweb.dto.request.PostRequestDto;
import com.egswebapp.egsweb.dto.response.PostResponse;
import com.egswebapp.egsweb.enums.CategoryErrorCode;
import com.egswebapp.egsweb.enums.PostErrorCode;
import com.egswebapp.egsweb.enums.UserErrorCode;
import com.egswebapp.egsweb.excpetions.CategoryException;
import com.egswebapp.egsweb.excpetions.PostException;
import com.egswebapp.egsweb.excpetions.UserException;
import com.egswebapp.egsweb.model.*;
import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.repasotory.CategoryRepository;
import com.egswebapp.egsweb.repasotory.PostContentRepository;
import com.egswebapp.egsweb.repasotory.PostRepository;
import com.egswebapp.egsweb.repasotory.UserRepository;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.PostService;
import com.egswebapp.egsweb.util.FileUtil;
import com.egswebapp.egsweb.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final PostContentRepository postContentRepository;


    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository, PostContentRepository postContentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postContentRepository = postContentRepository;

    }


    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {

        List<PostContent> contents = postContentRepository.findAll();
        log.info(contents.toString());
        List<PostResponse> postList = MapUtils.mapList(contents);
        log.info("All posts found success");
        return postList;
    }


    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByCategory(final Long id) {

        List<PostContent> contents = postContentRepository.findPostContentByPostCategoryId(id);
        if (CollectionUtils.isEmpty(contents)) {
            return Collections.EMPTY_LIST;
        }
        List<PostResponse> postList = MapUtils.mapList(contents);
        log.info("Posts by category found success");
        return postList;
    }


    @Override
    @Transactional
    public void addPost(final PostRequestDto postRequest,
                        final JwtUserDetails currentUser,
                        final MultipartFile[] uploadingFiles) {

        final User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        final Category category = categoryRepository.getCategoryByName(postRequest.getCategoryName());
        if (category == null) {
            log.info("Category with this id not exist");
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }
        Post post = new Post();
        post.setCategory(category);

        MultipartFile [] multipartFiles = FileUtil.uploadedFiles(uploadingFiles);

        String uploadedFileName = Arrays.stream(multipartFiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
        post.setImgName(uploadedFileName);

        PostContentId postContentId = new PostContentId();
        postContentId.setLanguage(Language.forName(postRequest.getLanguage()));

        PostContent postContent = new PostContent();
        postContent.setPostContentId(postContentId);
        postContent.setPost(post);
        postContent.setUser(user);
        postContent.setTitle(postRequest.getTitle());
        postContent.setShortText(postRequest.getShortText());
        postContent.setDescription(postRequest.getDescription());

        postRepository.save(post);
        postContentRepository.save(postContent);
        log.info("Post added success");
    }


    @Override
    @Transactional
    public PostResponse updatePost(
            final PostRequestDto postRequest,
            final JwtUserDetails currentUser,
            final String id
    ) {

        PostContentId postContentId = new PostContentId();
        postContentId.setPostId(id);
        postContentId.setLanguage(Language.forName(postRequest.getLanguage()));

        final PostContent postContent = postContentRepository.findById(postContentId).orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_EXIST));
        postContent.setTitle(postRequest.getTitle());
        postContent.setShortText(postRequest.getShortText());
        postContent.setDescription(postRequest.getDescription());
        final Category category = categoryRepository.getCategoryByName(postRequest.getCategoryName());
        if (category == null) {
            log.info("Category with this id not exist");
            throw new PostException(PostErrorCode.CATEGORY_NOT_EXIST);
        }
        postContent.getPost().setCategory(category);
        postContentRepository.save(postContent);
        PostResponse postResponse = MapUtils.map(postContent);
        log.info("Post  updated success");
        return postResponse;

    }

    @Override
    @Transactional
    public Post getPostById(final String id) {

        final Post post = postRepository.findById(id).orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_EXIST));
        log.info("Post found  successfully");
        return post;
    }


    @Override
    @Transactional
    public void deletePost(final String id) {

        int count = postContentRepository.deletePostContentByPostId(id);
        if (count != 1) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        postRepository.deleteById(id);
        log.info("Post deleted success");
    }
}

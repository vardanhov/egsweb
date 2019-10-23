package com.egswebapp.egsweb.controller;


import com.egswebapp.egsweb.dto.request.PostRequestDto;
import com.egswebapp.egsweb.dto.response.ApiResponse;
import com.egswebapp.egsweb.dto.response.PostResponse;
import com.egswebapp.egsweb.model.Post;
import com.egswebapp.egsweb.security.CurrentUser;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import com.egswebapp.egsweb.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "PostController Resource, functionality for user ", description = "shows post functionality")
@Slf4j
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    /**
     * Get all Posts with PostContent
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get all Posts with PostContent")
    public ResponseEntity<?> getAllPosts() {

        log.info("Trying to get all posts");
        List<PostResponse> postResponseList = postService.getAllPosts();
        return new ResponseEntity<>(postResponseList, HttpStatus.OK);
    }

    /**
     * Get list of posts by category id
     *
     * @param id the {@Link id} id of category
     */

    @GetMapping("/category/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get list of posts by category id")
    public ResponseEntity<?> getPostsByCategory(@PathVariable(name = "id") Long id) {

        log.info("trying to get posts by category id");
        List<PostResponse> postRepositoryList = postService.getPostsByCategory(id);
        return new ResponseEntity<>(postRepositoryList, HttpStatus.OK);
    }

    /**
     * Add Post with PostContent
     *
     * @param postRequest the {@Link postRequest}
     * @param currentUser the {@Link currentUser}
     * @param uploadingFiles        the {@Link uploadingFiles}
     */
    @PostMapping("/addPost")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Add Post with PostContent")
    public ResponseEntity<ApiResponse> addPost(@Valid @ModelAttribute PostRequestDto postRequest, @CurrentUser JwtUserDetails currentUser, @RequestParam("file") MultipartFile[] uploadingFiles) {

        log.info("trying to add post");
        postService.addPost(postRequest, currentUser, uploadingFiles);
        return new ResponseEntity<>(new ApiResponse(true, "post is created", postService.getAllPosts()), HttpStatus.CREATED);
    }

    /**
     * update Post
     *
     * @param postRequestDto the {@Link postRequestDto}
     * @param currentUser    the {@Link currentUser}
     * @param id             the {@Link id}
     */

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "update Post")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostRequestDto postRequestDto, @CurrentUser JwtUserDetails currentUser, @PathVariable(name = "id") String id) {

        log.info("trying to update post");
        PostResponse postResponse = postService.updatePost(postRequestDto, currentUser, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);

    }

    /**
     * Get post by post id
     *
     * @param id the {@Link id} id of post
     */

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get post by post id")
    public ResponseEntity<?> getPost(@PathVariable(name = "id") String id) {

        log.info("trying to get post by id");

        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * Delete post by post id
     *
     * @param id the {@Link id} id of post
     */

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Delete post by post id")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") String id) {

        log.info("trying to delete post");
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse(true, "You successfully deleted post"), HttpStatus.OK);
    }
}

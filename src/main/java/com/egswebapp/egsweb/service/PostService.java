package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.PostRequestDto;
import com.egswebapp.egsweb.dto.response.PostResponse;
import com.egswebapp.egsweb.model.Post;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    /**
     *
     *
     * Get all Posts with PostContent
     *
     */
    List<PostResponse> getAllPosts();



    /**
     *
     * Get list of posts by category id
     *
     *@param id  the {@Link id} id of category
     */
    List<PostResponse> getPostsByCategory(final Long id);



    /**
     *
     * Add Post with PostContent
     * @param postRequest  the {@Link postRequest}
     * @param currentUser  the {@Link currentUser}
     * @param uploadingFiles  the {@Link uploadingFiles}
     *
     */

    void addPost(final PostRequestDto postRequest, final JwtUserDetails currentUser, final MultipartFile[] uploadingFiles);

    /**
     *
     * update Post
     * @param postRequest  the {@Link postRequest}
     * @param currentUser  the {@Link currentUser}
     * @param id  the {@Link id}
     *
     */

    PostResponse updatePost(final PostRequestDto postRequest, final JwtUserDetails currentUser,final String id);


    /**
     *
     * Get post by post id
     *
     *@param id  the {@Link id} id of post
     */

    Post getPostById(final String id);


    /**
     *
     * Delete post by post id
     *
     *@param id  the {@Link id} id of post
     */

    void deletePost(final String id);


}

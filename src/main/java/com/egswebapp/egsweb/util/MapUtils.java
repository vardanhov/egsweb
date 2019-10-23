package com.egswebapp.egsweb.util;

import com.egswebapp.egsweb.dto.response.PageResponseDto;
import com.egswebapp.egsweb.dto.response.PostResponse;
import com.egswebapp.egsweb.model.PageContent;
import com.egswebapp.egsweb.model.PostContent;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {

    /**
     * Mapping PostContent list to PostResponse list
     */

    public static List<PostResponse> mapList(final List<PostContent> contents) {
        List<PostResponse> postResponseList = new ArrayList<>();
        for (int i = 0; i < contents.size(); i++) {
            PostContent postContent = contents.get(i);
            PostResponse postResponse = new PostResponse();
            postResponse.setId(postContent.getPost().getId());
            postResponse.setTitle(postContent.getTitle());
            postResponse.setDescription(postContent.getDescription());
            postResponse.setShortText(postContent.getShortText());
            postResponse.setCategory(postContent.getPost().getCategory().getName());
            postResponse.setLanguage(postContent.getPostContentId().getLanguage().getName());
            postResponse.setUserId(postContent.getUser().getId());
            postResponse.setImageName(postContent.getPost().getImgName());
            postResponse.setCreateDate(postContent.getPost().getCreateDate());
            postResponse.setUpdateDate(postContent.getUpdateDate());
            postResponseList.add(postResponse);
        }
        return postResponseList;
    }


    /**
     * Mapping PostContent  to PostResponse
     */
    public static PostResponse map(final PostContent contents) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(contents.getPost().getId());
        postResponse.setTitle(contents.getTitle());
        postResponse.setDescription(contents.getDescription());
        postResponse.setShortText(contents.getShortText());
        postResponse.setCategory(contents.getPost().getCategory().getName());
        postResponse.setLanguage(contents.getPostContentId().getLanguage().getName());
        postResponse.setUserId(contents.getUser().getId());
        postResponse.setImageName(contents.getPost().getImgName());
        postResponse.setCreateDate(contents.getPost().getCreateDate());
        postResponse.setUpdateDate(contents.getUpdateDate());
        return postResponse;
    }


    /**
     * Map as List PageContent for PageResponseDto
     * */
    public static List<PageResponseDto> mapAsList(List<PageContent> contents) {
        final List<PageResponseDto> responseDto = new ArrayList<>();
        contents.forEach(response -> responseDto.add(new PageResponseDto(
                response.getTitle(),
                response.getDescription(),
                response.getPageContentId().getLanguage().getName(),
                response.getPage().getId(),
                response.getUser().getId(),
                response.getPage().getImgName()
        )));
        return responseDto;
    }
}

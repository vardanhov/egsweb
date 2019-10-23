package com.egswebapp.egsweb.repasotory;


import com.egswebapp.egsweb.model.PostContent;
import com.egswebapp.egsweb.model.PostContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PostContentRepository extends JpaRepository<PostContent, PostContentId> {

    @Modifying
    @Query(value = "delete  FROM post_content as pc where pc.post_id=?1", nativeQuery = true)
    @Transactional
    int deletePostContentByPostId(final String id);

    List<PostContent> findPostContentByPostCategoryId(final Long id);
}

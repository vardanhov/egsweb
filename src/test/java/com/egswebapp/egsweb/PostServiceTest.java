package com.egswebapp.egsweb;


import com.egswebapp.egsweb.enums.CategoryErrorCode;
import com.egswebapp.egsweb.enums.PostErrorCode;
import com.egswebapp.egsweb.excpetions.CategoryException;
import com.egswebapp.egsweb.excpetions.PostException;
import com.egswebapp.egsweb.model.*;
import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.repasotory.CategoryRepository;
import com.egswebapp.egsweb.repasotory.PostContentRepository;
import com.egswebapp.egsweb.repasotory.PostRepository;
import com.egswebapp.egsweb.repasotory.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PostServiceTest {

    private final static String USER_ID = "8a81829c6d202f43016d203a06980000";
    private static String POST_ID;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostContentRepository postContentRepository;

    public PostServiceTest() {
    }


    @BeforeAll
    void setup() {
        User user = userRepository.findUserById(USER_ID);

        final Category category = categoryRepository.getCategoryByName("my category");
        if (category == null) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }
        Post post = new Post();
        post.setCategory(category);
        post.setImgName("newFile");

        PostContentId postContentId = new PostContentId();
        postContentId.setLanguage(Language.forName("Eng"));

        PostContent postContent = new PostContent();
        postContent.setPostContentId(postContentId);
        postContent.setPost(post);
        postContent.setUser(user);
        postContent.setTitle("Title");
        postContent.setShortText("Short text");
        postContent.setDescription("Description");

        post = postRepository.saveAndFlush(post);
        POST_ID = post.getId();
        postContentRepository.save(postContent);
    }


    @AfterAll
    void tear() {
        int count = postContentRepository.deletePostContentByPostId(POST_ID);
        if (count != 1) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        postRepository.deleteById(POST_ID);
    }


    @Test
    @Order(1)
    public void testGetAllPosts() {

        List<PostContent> posts = postContentRepository.findAll();
        assertNotNull(posts);

    }


    @Test
    @Order(2)
    public void testGetPostsByCategory() {

        final Long categoryId = 1L;
        List<PostContent> post = postContentRepository.findPostContentByPostCategoryId(categoryId);
        assertNotNull(post);
    }


    @Test
    @Order(4)
    public void testAddPost() {
        User user = userRepository.findUserById(USER_ID);

        final Category category = categoryRepository.getCategoryByName("my category");
        if (category == null) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }
        Post post = new Post();
        post.setCategory(category);
        post.setImgName("newFile");

        PostContentId postContentId = new PostContentId();
        postContentId.setLanguage(Language.forName("Eng"));

        PostContent postContent = new PostContent();
        postContent.setPostContentId(postContentId);
        postContent.setPost(post);
        postContent.setUser(user);
        postContent.setTitle("New Title");
        postContent.setShortText("New Short text");
        postContent.setDescription("New Description");

        post = postRepository.saveAndFlush(post);
        POST_ID = post.getId();

        postContent = postContentRepository.saveAndFlush(postContent);
        assertNotNull(post);
        assertNotNull(postContent);


    }

    @Test
    @Order(5)
    public void testUpdatePost() {
        PostContentId postContentId = new PostContentId();
        postContentId.setPostId(POST_ID);
        postContentId.setLanguage(Language.forName("Eng"));

        final PostContent postContent = postContentRepository.findById(postContentId).orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_EXIST));
        postContent.setTitle("New Title");
        postContent.setShortText("New Short text");
        postContent.setDescription("New Description");
        final Category category = categoryRepository.getCategoryByName("my category");
        if (category == null) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }
        postContent.getPost().setCategory(category);
        PostContent savedPostContent = postContentRepository.saveAndFlush(postContent);

        assertNotNull(postContent);
        assertEquals(postContent.getTitle(), savedPostContent.getTitle());
        assertEquals(postContent.getShortText(), savedPostContent.getShortText());
        assertEquals(postContent.getDescription(), savedPostContent.getDescription());


    }

    @Test
    @Order(6)
    public void testGetPostById() {

        final Post post = postRepository.findById(POST_ID).orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_EXIST));
        assertNotNull(post);
    }

    @Test
    @Order(3)
    public void testDeletePostById() {

        int count = postContentRepository.deletePostContentByPostId(POST_ID);
        if (count != 1) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        postRepository.deleteById(POST_ID);
        final Optional<Post> posts = postRepository.findById(POST_ID);
        boolean check = posts.isPresent();
        assertEquals(false, check);

    }
}

package com.egswebapp.egsweb.model;


import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "post_content")
public class PostContent implements Serializable {

    @EmbeddedId
    private PostContentId postContentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id", referencedColumnName="id", insertable = false, updatable = false)
    @MapsId("postId")

    private Post post;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName="id")

    private User user;


    @Column(name = "title")
    private String title;

    @Type(type = "text")
    @Column(name = "description")
    private String description;

    @Type(type = "text")
    @Column(name = "short_text")
    private String shortText;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Date updateDate;

    public PostContent() {
    }

    public PostContentId getPostContentId() {
        return postContentId;
    }

    public void setPostContentId(PostContentId postContentId) {
        this.postContentId = postContentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


}
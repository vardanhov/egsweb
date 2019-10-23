package com.egswebapp.egsweb.model;

import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.model.enums.convert.LanguagesConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class PostContentId implements Serializable {

    @Column(name = "post_id")
    protected String postId;


    @Column(name = "language_id")
    @Convert(converter = LanguagesConverter.class)
    protected Language language;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostContentId that = (PostContentId) o;
        return Objects.equals(postId, that.postId) &&
                language == that.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, language);
    }

}


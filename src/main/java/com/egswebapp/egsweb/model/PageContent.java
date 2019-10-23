package com.egswebapp.egsweb.model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "page_content")
public class PageContent implements Serializable {

    @EmbeddedId
    private PageContentId pageContentId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "page_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("pageId")
    private Page page;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "title")
    private String title;

    @Type(type = "text")
    @Column(name = "description")
    private String description;

    @Column(name = "update_date")
    private Date updateDate;

    public PageContent() {
    }

    public PageContent(PageContentId pageContentId, Page page, User user, String title, String description, Date updateDate) {
        this.pageContentId = pageContentId;
        this.page = page;
        this.user = user;
        this.title = title;
        this.description = description;
        this.updateDate = updateDate;
    }

    public PageContentId getPageContentId() {
        return pageContentId;
    }

    public void setPageContentId(PageContentId pageContentId) {
        this.pageContentId = pageContentId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

package com.egswebapp.egsweb.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class PostResponse {

	private String id;

    private String title;

    private String description;

	private String shortText;

    private String category;

    private String language;

    private String userId;

    private String imageName;

	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public String getShortText() { return shortText; }

	public void setShortText(String shortText) { this.shortText = shortText; }

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}

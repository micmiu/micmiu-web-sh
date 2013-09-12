package com.micmiu.framework.web.v1.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.micmiu.framework.anno.ShowParam;
import com.micmiu.framework.web.v1.base.entity.IdEntity;
import com.micmiu.modules.json.jackson.CustomDateSerializer;

/**
 * 
 * 用户.
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Entity
@Table(name = "T_BLOG")
public class Blog extends IdEntity {

	@ShowParam(value = "blog.gd.col.title")
	@Column(name = "TITLE", length = 255)
	private String title;

	@ShowParam("blog.gd.col.author")
	@Column(name = "AUTHOR", length = 20)
	private String author;

	@ShowParam("blog.gd.col.category")
	@Column(name = "CATEGORY", length = 50)
	private String category;

	@ShowParam(value = "blog.gd.col.url", width = 200)
	@Column(name = "URL", length = 512)
	private String url;

	@ShowParam("blog.gd.col.other")
	@Column(name = "OTHER", length = 1024)
	private String other;

	@ShowParam("blog.gd.col.creater")
	@Column(name = "CREATER", length = 20)
	private String creater;

	@ShowParam("blog.gd.col.publishDate")
	@Column(name = "PUBLISH_DATE")
	private Date publishDate;

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public String getUrl() {
		return url;
	}

	public String getOther() {
		return other;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
package org.nicolas.pojo;

import org.nicolas.util.BaseQuery;

import java.util.Date;

/**
 * @author zorth
 */
public class BlogList extends BaseQuery {
    private Integer id;
    private String title;
    private String author;
    private String createTime;
    private String updateTime;
    private Integer views;
    private Status status;
    private Integer commentTime;
    private String classification;

    public BlogList() {
    }

    public BlogList(String title, String author, String createTime, String updateTime, Integer views, Status status, Integer commentTime, String classification) {
        this.title = title;
        this.author = author;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.views = views;
        this.status = status;
        this.commentTime = commentTime;
        this.classification = classification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Integer commentTime) {
        this.commentTime = commentTime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}

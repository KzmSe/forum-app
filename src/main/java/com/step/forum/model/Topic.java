package com.step.forum.model;

import com.step.forum.util.TimeUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Topic {

    private int id;
    private String title;
    private String description;
    private LocalDateTime shareDate;
    private int viewCount;
    private User user;
    private List<Comment> commentList;

    public Topic() {
        commentList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getShareDate() {
        return shareDate;
    }

    public void setShareDate(LocalDateTime shareDate) {
        this.shareDate = shareDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", shareDate=" + shareDate +
                ", viewCount=" + viewCount +
                ", user=" + user +
                ", commentList=" + commentList +
                '}';
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }
}

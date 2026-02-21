package com.lynx.testovoe.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostWithCommentsResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Integer comCounter;
    private List<CommentResponse> comments;

    public PostWithCommentsResponse() {}

    public PostWithCommentsResponse(Long id, String title, String content, LocalDateTime createdAt, Integer comCounter, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comCounter = comCounter;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getComCounter() {
        return comCounter;
    }
    public void setComCounter(Integer comCounter) {
        this.comCounter = comCounter;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }
    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
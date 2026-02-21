package com.lynx.testovoe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentCreateRequest {

    @NotBlank
    private String text;

    @NotNull
    private Long postId;

    private Long parentCommentId;

    public CommentCreateRequest() {}

    public CommentCreateRequest(String text, Long postId, Long parentCommentId) {
        this.text = text;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
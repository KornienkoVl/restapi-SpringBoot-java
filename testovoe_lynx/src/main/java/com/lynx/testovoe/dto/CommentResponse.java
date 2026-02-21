package com.lynx.testovoe.dto;

import com.lynx.testovoe.entity.CommentStatus;
import java.util.List;

public class CommentResponse {
    private Long id;
    private String text;
    private CommentStatus status;
    private Long postId;
    private Long parentCommentId;
    private List<CommentResponse> replies;

    public CommentResponse() {}

    public CommentResponse(Long id, String text, CommentStatus status, Long postId, Long parentCommentId, List<CommentResponse> replies) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.replies = replies;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public CommentStatus getStatus() {
        return status;
    }
    public void setStatus(CommentStatus status) {
        this.status = status;
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

    public List<CommentResponse> getReplies() {
        return replies;
    }
    public void setReplies(List<CommentResponse> replies) {
        this.replies = replies;
    }
}
package com.lynx.testovoe.controller;

import com.lynx.testovoe.dto.CommentCreateRequest;
import com.lynx.testovoe.dto.CommentResponse;
import com.lynx.testovoe.service.CommentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController{
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentCreateRequest request){
        CommentResponse created = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
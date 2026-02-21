package com.lynx.testovoe.controller;

import com.lynx.testovoe.dto.PostCreateRequest;
import com.lynx.testovoe.dto.PostResponse;
import com.lynx.testovoe.dto.PostWithCommentsResponse;
import com.lynx.testovoe.service.PostService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {
        return postService.getAllPosts(sortBy, direction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithCommentsResponse> getPostWithComments(@PathVariable long id){
        PostWithCommentsResponse post = postService.getPostWithComments(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostCreateRequest request){
        PostResponse created = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeletePost(@PathVariable Long id){
        postService.softDeletePost(id);
        return ResponseEntity.noContent().build();
    }
}
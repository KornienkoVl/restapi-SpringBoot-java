package com.lynx.testovoe.service;

import com.lynx.testovoe.dto.*;
import com.lynx.testovoe.entity.Comment;
import com.lynx.testovoe.entity.CommentStatus;
import com.lynx.testovoe.entity.Post;
import com.lynx.testovoe.exception.ResourceNotFoundException;
import com.lynx.testovoe.repository.CommentRepository;
import com.lynx.testovoe.repository.PostRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostResponse> getAllPosts(String sortBy, String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy.equals("comCounter") ? "comCounter" : "createdAt");
        List<Post> posts = postRepository.findAll(sort); //удаленные автоматически не входят в запрос
        return posts.stream().map(this::mapToPostResponse).collect(Collectors.toList());
    }

    public PostWithCommentsResponse getPostWithComments(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пост не найден или удален."));

        //Дальше все комментарии со статусом approved к посту
        List<Comment> allComments = commentRepository.findByPostIdAndStatus(id, CommentStatus.APPROVED);
        //Дальше нужно сформировать комментарии к посту и ответы к комментариям
        Map<Long, List<Comment>> repliesByParent = allComments.stream() //ответы
                .filter(c -> c.getParentComment() != null)
                .collect(Collectors.groupingBy(c -> c.getParentComment().getId()));
        List<CommentResponse> parentComments = allComments.stream() //комментарии к посту
                .filter(c -> c.getParentComment() == null)
                .map(c -> buildCommentResponse(c, repliesByParent.getOrDefault(c.getId(), Collections.emptyList())))
                .collect(Collectors.toList());

        return new PostWithCommentsResponse(
                post.getID(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getComCounter(),
                parentComments
        );
    }

    @Transactional
    public PostResponse createPost(PostCreateRequest request){
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setIsDeleted(false);
        post.setComCounter(0);
        Post saved = postRepository.save(post);
        return mapToPostResponse(saved);
    }

    @Transactional
    public void softDeletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Пост не найден или уже удален."));
        post.setIsDeleted(true);
        postRepository.save(post);
    }

    private PostResponse mapToPostResponse(Post post){
        return new PostResponse(
                post.getID(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getComCounter()
        );
    }

    private CommentResponse buildCommentResponse(Comment comment, List<Comment> replies){
        List<CommentResponse> replyDtos = replies.stream()
                .map(r-> new CommentResponse(
                        r.getId(),
                        r.getText(),
                        r.getStatus(),
                        r.getPost().getID(),
                        r.getParentComment() != null ? r.getParentComment().getId() : null,
                        Collections.emptyList()
                )).toList();
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getStatus(),
                comment.getPost().getID(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                replyDtos
        );
    }
}

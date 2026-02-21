package com.lynx.testovoe.service;

import com.lynx.testovoe.dto.CommentCreateRequest;
import com.lynx.testovoe.dto.CommentResponse;
import com.lynx.testovoe.entity.Comment;
import com.lynx.testovoe.entity.CommentStatus;
import com.lynx.testovoe.entity.Post;
import com.lynx.testovoe.exception.CommentCreationException;
import com.lynx.testovoe.repository.CommentRepository;
import com.lynx.testovoe.repository.PostRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(CommentCreateRequest request){
        //Проверяем наличие поста
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(()-> new CommentCreationException("Пост не найден или удален."));

        //Проверяем наличие указанного родительского комментария
        Comment parent = null;
        if(request.getParentCommentId() != null){
            parent = commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(()-> new CommentCreationException("Родительского комментария не существует"));

            //родительский комментарий должен иметь статус approved
            if (parent.getStatus() != CommentStatus.APPROVED){
                throw new CommentCreationException("Можно отвечать только на подтвержденные комментарии.");
            }

            //указанный родительский комментарий не должен быть ответом
            if (parent.getParentComment() != null){
                throw new CommentCreationException("Превышение максимальной глубины вложенности комментариев.");
            }

            //указанный родительский комментарий должен относиться к тому же посту
            if(!parent.getPost().getID().equals(post.getID())){
                throw new CommentCreationException("Родительский комментарий относится к другому посту");
            }
        }

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setPost(post);
        comment.setParentComment(parent);
        comment.setStatus(CommentStatus.PENDING);

        Comment saved = commentRepository.save(comment);
        return mapToResponse(saved);
    }

    private CommentResponse mapToResponse(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getStatus(),
                comment.getPost().getID(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                Collections.emptyList()
        );
    }
}
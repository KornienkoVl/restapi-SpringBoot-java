package com.lynx.testovoe.repository;

import com.lynx.testovoe.entity.Comment;
import com.lynx.testovoe.entity.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndStatus(Long postId, CommentStatus status);
}

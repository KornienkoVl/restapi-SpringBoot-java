package com.lynx.testovoe.repository;

import com.lynx.testovoe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{ }

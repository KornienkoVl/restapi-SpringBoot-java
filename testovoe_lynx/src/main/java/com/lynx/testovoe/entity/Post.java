package com.lynx.testovoe.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@SQLRestriction("is_deleted = false") //автоматически исключает "мягко" удаленные посты из запросов к БД
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "com_counter", nullable = false)
    private Integer comCounter = 0;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public Post(){}

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Long getID() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}

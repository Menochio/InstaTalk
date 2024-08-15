package com.InstaTalk.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    public Comment() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(List<User> likedUser) {
        this.likedUser = likedUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Comment(Integer id, String content, User user, List<User> likedUser, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.likedUser = likedUser;
        this.createdAt = createdAt;
    }

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likedUser = new ArrayList<>();

    private LocalDateTime createdAt;

}

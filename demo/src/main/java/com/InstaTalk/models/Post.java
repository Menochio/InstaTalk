package com.InstaTalk.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany
    public List<Comment> comments = new ArrayList<>();

    public List<Integer> getPostsUsersLiked() {
        return postsUsersLiked;
    }

    public void setPostsUsersLiked(List<Integer> postsUsersLiked) {
        this.postsUsersLiked = postsUsersLiked;
    }

    public List<Integer> getPostsUsersSaved() {
        return postsUsersSaved;
    }

    public void setPostsUsersSaved(List<Integer> postsUsersSaved) {
        this.postsUsersSaved = postsUsersSaved;
    }

    public Post() {
    }

    public Post(List<Comment> comments, Integer id, String caption, String image, String video, User user, LocalDateTime createdAt, List<Integer> postsUsersLiked, List<Integer> postsUsersSaved) {
        this.comments = comments;
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.createdAt = createdAt;
        this.postsUsersLiked = postsUsersLiked;
        this.postsUsersSaved = postsUsersSaved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    @ManyToOne
    private User user;
    private LocalDateTime createdAt;

    private List<Integer> postsUsersLiked = new ArrayList<>();

    private List<Integer> postsUsersSaved = new ArrayList<>();
}

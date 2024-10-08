package com.InstaTalk.repositories;

import com.InstaTalk.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select posts from Post posts where posts.user.id=:userId")
    public List<Post> findPostByUserId(Integer userId);
}

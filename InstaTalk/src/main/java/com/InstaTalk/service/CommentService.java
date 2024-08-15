package com.InstaTalk.service;

import com.InstaTalk.models.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment likedComment(Integer commentId, Integer userId) throws Exception;

    public Comment findCommentById(Integer CommentId, Integer userId) throws Exception;
}

package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Comment;
import com.InstaTalk.models.Post;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.CommentRepository;
import com.InstaTalk.repositories.PostRepository;
import com.InstaTalk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment likedComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId, userId);
        User user = userService.findUserById(userId);
        if (!comment.getLikedUser().contains(user)) {
            comment.getLikedUser().add(user);
        } else comment.getLikedUser().remove(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId, Integer userId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new Exception("Comment not exist");
        }
        return comment.get();
    }
}

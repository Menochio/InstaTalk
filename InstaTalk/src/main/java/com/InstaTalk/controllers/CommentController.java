package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.CommentServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Comment;
import com.InstaTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/createComment/post/{postId}")
    public Comment createComment(@PathVariable Integer postId, @RequestBody Comment comment, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment newComment = commentService.createComment(comment, postId, user.getId());
        return newComment;
    }

    @PutMapping("/likeComment/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likedComment(commentId, user.getId());
        return likedComment;
    }

}

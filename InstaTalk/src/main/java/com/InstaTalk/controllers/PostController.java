package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.PostServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Post;
import com.InstaTalk.models.User;
import com.InstaTalk.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post, loggedInUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        String msg = postService.deletePost(postId, loggedInUser.getId());
        ApiResponse response = new ApiResponse(msg, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/user")
    public ResponseEntity<List<Post>> findUsersPost(@RequestHeader("Authorization") String jwt) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        List<Post> posts = postService.findPostByUserId(loggedInUser.getId());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() throws Exception {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/post/{postId}")
    public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        Post post = postService.savePost(postId, loggedInUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/like/post/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, loggedInUser.getId());
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}

package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Post;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.PostRepository;
import com.InstaTalk.repositories.UserRespository;
import com.InstaTalk.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRespository userRespository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCaption(post.getCaption());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userService.findUserById(userId));
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getId() != userId) {
            throw new Exception("You cannot delete others post");
        }
        postRepository.deleteById(postId);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws Exception {
        List<Post> posts = postRepository.findPostByUserId(userId);
        return posts;
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return post.get();
        }
        throw new Exception("Post not found by this id " + postId);
    }

    @Override
    public List<Post> findAllPost() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts;
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (user.getSavedPosts().contains(post.getId())) {
            user.getSavedPosts().remove(post.getId());
            post.getPostsUsersSaved().remove(user.getId());
        } else {
            user.getSavedPosts().add(post.getId());
            post.getPostsUsersSaved().add(user.getId());
        }
        userRespository.save(user);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getPostsUsersLiked().contains(userId)) {
            post.getPostsUsersLiked().remove(userId);
            user.getLikedPosts().remove(userId);
        } else {
            post.getPostsUsersLiked().add(userId);
            user.getLikedPosts().add(postId);
        }
        userRespository.save(user);
        return postRepository.save(post);
    }
}

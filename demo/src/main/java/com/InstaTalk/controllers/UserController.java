package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRespository userRespository;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/getUser")
    public User getUser(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userRespository.findAll();
        return users;
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, loggedInUser.getId());
        return updatedUser;
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        String message = userService.deleteUserById(user.getId());
        return message;
    }

    @PutMapping("/user/{userToBeFollowed}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userToBeFollowed) throws Exception {
        User loggedInUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(userToBeFollowed, loggedInUser.getId());
        return user;
    }

    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> searchedUsers = userService.searchUser(query);
        return searchedUsers;
    }

    @GetMapping("/user/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return user;
    }
}

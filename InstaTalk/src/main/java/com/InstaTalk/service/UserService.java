package com.InstaTalk.service;

import com.InstaTalk.exception.UserException;
import com.InstaTalk.models.User;

import java.util.List;


public interface UserService {
    public User registerUser(User user);

    public User findUserById(Integer userId) throws UserException;

    public User findUserByEmail(String email);

    public User followUser(Integer idOfUserToBeFollowed, Integer idOfUserWhoHasToFollow) throws Exception;

    public User updateUser(User user, Integer userId) throws UserException;

    public List<User> searchUser(String query);

    public String deleteUserById(Integer userId) throws UserException;

    public User findUserByJwt(String jwt);
}

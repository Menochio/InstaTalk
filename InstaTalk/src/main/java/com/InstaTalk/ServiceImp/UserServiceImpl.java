package com.InstaTalk.ServiceImp;

import com.InstaTalk.config.JwtProvider;
import com.InstaTalk.exception.UserException;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.UserRespository;
import com.InstaTalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRespository userRespository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        newUser.setLastName(user.getLastName());
        newUser.setId(user.getId());
        newUser.setUserFollowers(user.getUserFollowers());
        newUser.setUsersFollowed(user.getUsersFollowed());
        newUser.setGender(user.getGender());
        User savedUser = userRespository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRespository.findById(userId);
        if (user.isPresent()) return user.get();
        throw new UserException("User does not exist with userId " + userId);
    }

    @Override
    public User findUserByEmail(String userEmail) {
        User user = userRespository.findByEmail(userEmail);
        return user;
    }

    @Override
    public User followUser(Integer idOfUserToBeFollowed, Integer idOfUserWhoHasToFollow) throws Exception {
        User userToBeFollowed = findUserById(idOfUserToBeFollowed);
        User userFollowing = findUserById(idOfUserWhoHasToFollow);
        userFollowing.getUsersFollowed().add(userToBeFollowed.getId());
        userToBeFollowed.getUserFollowers().add(userFollowing.getId());
        userRespository.save(userToBeFollowed);
        userRespository.save(userFollowing);
        return userFollowing;
    }

    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> oldUserDetails = userRespository.findById(userId);
        if (oldUserDetails.isPresent()) {
            User userDetail = oldUserDetails.get();
            if (user.getFirstName() != null) userDetail.setFirstName(user.getFirstName());
            if (user.getLastName() != null) userDetail.setLastName(user.getLastName());
            if (user.getPassword() != null) userDetail.setPassword(user.getPassword());
            if (user.getEmail() != null) userDetail.setEmail(user.getEmail());
            if (user.getGender() != null) userDetail.setGender(user.getGender());
            if (user.getUserFollowers() != null) userDetail.setUserFollowers(user.getUserFollowers());
            if (user.getUsersFollowed() != null) userDetail.setUsersFollowed(user.getUsersFollowed());
            userRespository.save(userDetail);
            return userDetail;
        }
        throw new UserException("User with id does not exist");
    }

    public String deleteUserById(Integer userId) throws UserException {
        Optional<User> user = userRespository.findById(userId);
        if (user.isPresent()) {
            userRespository.deleteById(userId);
            return "User deleted Successfully";
        }
        throw new Error("User not find With given userId");
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRespository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> searchedUsers = userRespository.searchUser(query);
        return searchedUsers;
    }
}

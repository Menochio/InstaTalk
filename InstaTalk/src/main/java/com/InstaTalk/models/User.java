package com.InstaTalk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String gender;

    public List<Integer> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Integer> savedPosts) {
        this.savedPosts = savedPosts;
    }

    private List<Integer> savedPosts = new ArrayList<>();

    public List<Integer> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Integer> likedPosts) {
        this.likedPosts = likedPosts;
    }

    private List<Integer> likedPosts = new ArrayList<>();

    private List<Integer> usersFollowed;

    private List<Integer> userFollowers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getUsersFollowed() {
        return usersFollowed;
    }

    public void setUsersFollowed(List<Integer> usersFollowed) {
        this.usersFollowed = usersFollowed;
    }

    public List<Integer> getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(List<Integer> userFollowers) {
        this.userFollowers = userFollowers;
    }


    public User(Integer id, String firstName, String lastName, String email, String password, String gender, List<Integer> savedPosts, List<Integer> likedPosts, List<Integer> usersFollowed, List<Integer> userFollowers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.savedPosts = savedPosts;
        this.likedPosts = likedPosts;
        this.usersFollowed = usersFollowed;
        this.userFollowers = userFollowers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {

    }
}

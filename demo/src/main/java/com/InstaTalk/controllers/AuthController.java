package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.config.JwtProvider;
import com.InstaTalk.models.Post;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.UserRespository;
import com.InstaTalk.request.LoginRequest;
import com.InstaTalk.response.AuthResponse;
import com.InstaTalk.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRespository userRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        List<Integer> followers = new ArrayList<>();
        List<Integer> followed = new ArrayList<>();
        List<Integer> savedPosts = new ArrayList<>();
        List<Integer> likedPosts = new ArrayList<>();
        User userAleradyExist = userRespository.findByEmail(user.getEmail());
        if (userAleradyExist != null) {
            throw new Exception("This email already used by another account");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setLastName(user.getLastName());
        newUser.setUserFollowers(followers);
        newUser.setUsersFollowed(followed);
        newUser.setGender(user.getGender());
        newUser.setLikedPosts(likedPosts);
        newUser.setSavedPosts(savedPosts);
        User savedUser = userRespository.save(newUser);
        List<GrantedAuthority> authorities = new ArrayList<>();

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser, savedUser.getPassword(), authorities);
        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, "User Registered Successfully !");
        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, "User LoggedIn Successfully !");
        return authResponse;
    }

    private Authentication authenticate(String email, String password) {
        User userDetails = userService.findUserByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
}

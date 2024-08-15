package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.ReelsServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Reels;
import com.InstaTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelsController {

    @Autowired
    private ReelsServiceImpl reelsService;


    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createReel")
    public Reels createdReels(@RequestHeader("Authorization") String jwt, @RequestBody Reels reels) {
        User user = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reels, user);
        return createdReels;
    }

    @GetMapping("/allReels")
    public List<Reels> createdReels() {
        return reelsService.findAllReels();
    }

    @GetMapping("/userReels")
    public List<Reels> findUserReels(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return reelsService.findUserReels(user.getId());
    }
}

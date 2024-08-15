package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.StoryServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Story;
import com.InstaTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoryController {

    @Autowired
    private StoryServiceImpl storyService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createStory")
    public Story createUser(@RequestHeader("Authorization") String jwt, Story story) {
        User user = userService.findUserByJwt(jwt);
        Story createStory = storyService.createStory(user, story);
        return createStory;
    }

    @GetMapping("/getAllUsersStory")
    public List<Story> getUsersStory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return storyService.findStoryByUserId(user.getId());
    }
}

package com.InstaTalk.service;

import com.InstaTalk.models.Story;
import com.InstaTalk.models.User;

import java.util.List;


public interface StoryService {

    public Story createStory(User user, Story story);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}

package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Story;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.StoryRepository;
import com.InstaTalk.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    StoryRepository storyRepository;

    @Override
    public Story createStory(User user, Story story) {
        Story createStory = new Story();
        createStory.setCreatedAt(LocalDateTime.now());
        createStory.setImage(story.getImage());
        createStory.setCaption(story.getCaption());
        createStory.setUser(user);
        storyRepository.save(createStory);
        return createStory;
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        List<Story> usersStory = storyRepository.findByUserId(user.getId());
        return usersStory;
    }
}

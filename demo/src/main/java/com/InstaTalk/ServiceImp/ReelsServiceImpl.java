package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Reels;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.ReelsRepository;
import com.InstaTalk.service.ReelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    ReelsRepository reelsRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();
        createReel.setUser(user);
        createReel.setTitle(reel.getTitle());
        createReel.setVideo(reel.getVideo());
        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}

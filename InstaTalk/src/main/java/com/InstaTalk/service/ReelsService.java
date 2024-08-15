package com.InstaTalk.service;

import com.InstaTalk.models.Reels;
import com.InstaTalk.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUserReels(Integer userId) throws Exception;

}

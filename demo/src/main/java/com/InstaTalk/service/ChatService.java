package com.InstaTalk.service;

import com.InstaTalk.models.Chat;
import com.InstaTalk.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User userChatting, User userToBeChatted);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}

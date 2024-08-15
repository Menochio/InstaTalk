package com.InstaTalk.service;

import com.InstaTalk.models.Chat;
import com.InstaTalk.models.Message;
import com.InstaTalk.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}

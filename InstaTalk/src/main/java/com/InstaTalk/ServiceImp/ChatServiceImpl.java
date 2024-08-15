package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Chat;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.ChatRespository;
import com.InstaTalk.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRespository chatRespository;

    @Autowired
    private UserServiceImpl userService;


    @Override
    public Chat createChat(User userChatting, User userToBeChatted) {
        Chat isExisting = chatRespository.findIfChatExist(userToBeChatted, userChatting);
        if (isExisting != null) {
            return isExisting;
        }
        Chat chat = new Chat();
        chat.getUser().add(userChatting);
        chat.getUser().add(userToBeChatted);
        chat.setChatImage(chat.getChatImage());
        chat.setCreatedAt(LocalDateTime.now());
        return chatRespository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRespository.findById(chatId);
        if (chat.isEmpty()) {
            throw new Exception("Chat not found with Id " + chatId);
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        List<Chat> userChats = chatRespository.findByUserId(userId);
        return userChats;
    }
}

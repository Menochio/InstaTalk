package com.InstaTalk.ServiceImp;

import com.InstaTalk.models.Chat;
import com.InstaTalk.models.Message;
import com.InstaTalk.models.User;
import com.InstaTalk.repositories.ChatRespository;
import com.InstaTalk.repositories.MessageRepository;
import com.InstaTalk.service.ChatService;
import com.InstaTalk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRespository chatRespository;

    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {
        Chat chat = chatService.findChatById(chatId);


        Message mesg = new Message();
        mesg.setChat(chat);
        mesg.setContent(message.getContent());
        mesg.setImage(message.getImage());
        mesg.setUser(user);
        mesg.setCreateAt(LocalDateTime.now());
        chat.getMessages().add(mesg);
        chatRespository.save(chat);
        return messageRepository.save(mesg);
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chat.getId());
    }
}

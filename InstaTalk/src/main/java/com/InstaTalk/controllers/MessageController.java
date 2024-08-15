package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.MessageServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Message;
import com.InstaTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping("/message/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @RequestBody Message message, @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Message savedMessage = messageService.createMessage(user, chatId, message);
        return savedMessage;
    }

    @GetMapping("/message/chat/{chatId}")
    public List<Message> findChatMessages(@PathVariable Integer chatId) throws Exception {
        List<Message> savedMessages = messageService.findChatsMessages(chatId);
        return savedMessages;
    }


}

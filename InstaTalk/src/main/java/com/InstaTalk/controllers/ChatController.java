package com.InstaTalk.controllers;

import com.InstaTalk.ServiceImp.ChatServiceImpl;
import com.InstaTalk.ServiceImp.UserServiceImpl;
import com.InstaTalk.models.Chat;
import com.InstaTalk.models.User;
import com.InstaTalk.request.ChatRequest;
import com.InstaTalk.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/createChat")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody ChatRequest chatRequest) throws Exception {
        User user = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(chatRequest.getUserId());
        Chat chat = chatService.createChat(user, user2);
        return chat;
    }

    @GetMapping("/getAllUserChats")
    public List<Chat> getAllChat(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return chatService.findUsersChat(user.getId());
    }


}

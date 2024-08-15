package com.InstaTalk.repositories;

import com.InstaTalk.models.Chat;
import com.InstaTalk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRespository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUserId(Integer userId);

    @Query("select c from Chat c Where :userToBeChatted Member of c.user And :userChatting Member of c.user")
    public Chat findIfChatExist(@Param("userToBeChatted") User userToBeChatted, @Param("userChatting") User userChatting);

}

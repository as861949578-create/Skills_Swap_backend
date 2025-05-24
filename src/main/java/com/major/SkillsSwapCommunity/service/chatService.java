package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class chatService {
    @Autowired
    private chatRoomRepo ChatRoomRepo;


    public void createChatRoom(ChatRoom chatroom){
        ChatRoomRepo.save(chatroom);
    }


}

package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.repository.chatMessageRepo;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class chatService {
    @Autowired
    private chatRoomRepo ChatRoomRepo;

    @Autowired
    private chatMessageRepo ChatMessageRepo;

    public void createChatRoom(ChatRoom chatroom){
        ChatRoomRepo.save(chatroom);
    }

    public Optional<ChatRoom> findById(String chatRoomId){
       return ChatRoomRepo.findById(chatRoomId);
    }

    public List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(String chatRoomId){
        return ChatMessageRepo.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);
    }


}

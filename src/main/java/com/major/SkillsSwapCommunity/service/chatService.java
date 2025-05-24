package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.repository.chatMessageRepo;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.repository.authRepo;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.major.SkillsSwapCommunity.dto.ChatThreadDto;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Optional;

@Component
public class chatService {
    @Autowired
    private chatRoomRepo ChatRoomRepo;

    @Autowired
    private chatMessageRepo ChatMessageRepo;

        @Autowired
        private authRepo userRepo;

    public Optional<ChatRoom> findById(String chatRoomId){
       return ChatRoomRepo.findById(chatRoomId);
    }

    public List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(String chatRoomId){
        return ChatMessageRepo.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);
    }

    public List<ChatThreadDto> getUserChatRooms(String userId) {
        List<ChatRoom> rooms = ChatRoomRepo.findByUser1IdOrUser2Id(userId, userId);
        List<ChatThreadDto> chatThreads = new ArrayList<>();
            for (ChatRoom room : rooms) {
                ChatThreadDto dto = new ChatThreadDto();
                dto.setChatRoomId(room.getId());
                UserDetails user;
                if(room.getUser1Id() != userId)
                 user = userRepo.findById(room.getUser1Id()).orElse(null);
                else  user = userRepo.findById(room.getUser2Id()).orElse(null);
                dto.setUser(user);
                chatThreads.add(dto);
            }

            return chatThreads;
        }
    }
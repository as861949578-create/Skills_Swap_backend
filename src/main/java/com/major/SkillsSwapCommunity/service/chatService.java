package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.entity.swapRequest;
import com.major.SkillsSwapCommunity.repository.chatMessageRepo;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.repository.authRepo;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import com.major.SkillsSwapCommunity.repository.swapRequestsRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.major.SkillsSwapCommunity.dto.ChatThreadDto;

import java.util.*;

import java.util.List;

@Component
public class chatService {
    @Autowired
    private chatRoomRepo ChatRoomRepo;

    @Autowired
    private chatMessageRepo ChatMessageRepo;
    @Autowired
    private swapRequestsRepo swapRequestsRepo;

    @Autowired
    private authRepo userRepo;

    public void createChatRoom(ChatRoom chatroom){
        ChatRoomRepo.save(chatroom);
    }

    public Optional<ChatRoom> findById(String chatRoomId){
       return ChatRoomRepo.findById(chatRoomId);
    }

    public List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(String chatRoomId){
        return ChatMessageRepo.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);
    }

    public void saveMessages(ChatMessage chatmessage){
        ChatMessageRepo.save(chatmessage);
    }


    public List<ChatThreadDto> getUserChatRooms(String userId , String email) {
        List<ChatRoom> rooms = ChatRoomRepo.findByUser1IdOrUser2Id(userId, userId);
        Collections.sort(rooms , Comparator.comparing(ChatRoom::getTimestamp).reversed());
//        System.out.println(userId);
//        System.out.println(email);
//        System.out.println(rooms);
        List<ChatThreadDto> chatThreads = new ArrayList<>();
            for (ChatRoom room : rooms) {

//                System.out.println("user1");
//                System.out.println(room.getUser1Id());
//                System.out.println("user2");
//                System.out.println(room.getUser2Id());
//                System.out.println(room.getSwapRequestId());
                ChatThreadDto dto = new ChatThreadDto();
                dto.setChatRoomId(room.getId());
                UserDetails user;
                String Swapid = room.getSwapRequestId();
                ObjectId SwapobjectId = new ObjectId(Swapid);
                Optional<swapRequest> swap = swapRequestsRepo.findById(SwapobjectId);
                String skillset = null;
                String offeredSkill;
                String requestedSkill;
                if(swap.isPresent())
                {
                    swapRequest data = swap.get();
                    if(data.getSenderID().equals(email))
                    {
                        offeredSkill = data.getOfferedSkill();
                        requestedSkill = data.getRequestedSkill();
                    }else
                    {
                        offeredSkill = data.getRequestedSkill();
                        requestedSkill = data.getOfferedSkill();
                    }
                    dto.setRequestedSkill(requestedSkill);
                    dto.setOfferedSkill(offeredSkill);
                }
                if(!(room.getUser1Id().equals(userId)) ) {

                    user = userRepo.findById(room.getUser1Id()).orElse(null);
                }
                else  user = userRepo.findById(room.getUser2Id()).orElse(null);
                dto.setUser(user);
                chatThreads.add(dto);
            }

            return chatThreads;
        }
    }
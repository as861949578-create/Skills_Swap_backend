package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import com.major.SkillsSwapCommunity.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.major.SkillsSwapCommunity.dto.ChatThreadDto;
import java.util.List;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class chatController {

// chatroom created -- save into db into collection chatroom
 //get all chats -- side baar
 // particular user ke liye chat , jab wo click kre to
 //send message
@Autowired
private jwtUtils JwtUtils;

    @Autowired
    private chatService ChatService;
    @GetMapping("/rooms/{userId}")
public ResponseEntity<?> getUserChatRooms(@PathVariable String userId , @RequestHeader("Authorization") String tokenHeader) {
        try{
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Missing token", null));
        }
        String token = tokenHeader.substring(7);
        String email = JwtUtils.extractEmail(token);
            List<com.major.SkillsSwapCommunity.dto.ChatThreadDto> rooms = ChatService.getUserChatRooms(userId , email);
            return ResponseEntity.ok(new ApiResponse<>(true, "fetched all chatrooms successfully", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to get chatrooms", null));
        }

}



    @PostMapping("/create-chat-room")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom chatRoom) {
        try {
            ChatService.createChatRoom(chatRoom);
            return ResponseEntity.ok(new ApiResponse<>(true, "Chat room created successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to create chat room", null));
        }
    }

    @GetMapping("/room/messages")
    public ResponseEntity<?> getChatMessages(@RequestParam String chatRoomId) {
        try {
            Optional<ChatRoom> chatRoomOptional = ChatService.findById(chatRoomId);
            if (chatRoomOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Chat room not found", null));
            }

            List<ChatMessage> messages = ChatService.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);

            return ResponseEntity.ok(new ApiResponse<>(true, "Messages fetched successfully", messages));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to fetch messages", e.getMessage()));
        }
    }









}

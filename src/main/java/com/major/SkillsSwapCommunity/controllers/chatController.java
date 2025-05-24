package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
   private chatService ChatService;

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

    @GetMapping("/chat/messages")
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

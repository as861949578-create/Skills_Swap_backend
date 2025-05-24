package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
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
    @GetMapping("/rooms/{userId}")
public ResponseEntity<?> getUserChatRooms(@PathVariable String userId) {
        try {
            List<com.major.SkillsSwapCommunity.dto.ChatThreadDto> rooms = ChatService.getUserChatRooms(userId);
            return ResponseEntity.ok(new ApiResponse<>(true, "fetched all chatrooms successfully", null));
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




}

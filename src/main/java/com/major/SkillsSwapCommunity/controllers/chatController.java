package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ChatRoom;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class chatController {

// chatroom created -- save into db into collection chatroom
 //get all chats -- side baar
 // particular user ke liye chat , jab wo click kre to
 //send message
public ResponseEntity<List<ChatRoom>> getUserChatRooms(@PathVariable String userId) {
    List<ChatRoom> rooms = chatRoomRepository.findByUser1IdOrUser2Id(userId, userId);
    return ResponseEntity.ok(rooms);
}

}

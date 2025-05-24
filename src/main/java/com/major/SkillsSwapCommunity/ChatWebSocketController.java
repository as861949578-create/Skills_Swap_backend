package com.major.SkillsSwapCommunity;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.ChatRoom;
import com.major.SkillsSwapCommunity.repository.chatRoomRepo;
import com.major.SkillsSwapCommunity.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
public class ChatWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private chatService ChatService;

    @Autowired
    private chatRoomRepo chatRoomRepo;

    @MessageMapping("/chat.sendMessage") // POST from /app/chat.sendMessage
    public void sendMessage(@Payload ChatMessage message) {
            Optional<ChatRoom> chatRoom = chatRoomRepo.findById(message.getChatRoomId());
            if(chatRoom.isPresent())
            {
                ChatRoom newchatRoom = chatRoom.get();
                newchatRoom.setTimestamp(LocalDateTime.now());
                chatRoomRepo.save(newchatRoom);
            }
            ChatService.saveMessages(message);

        messagingTemplate.convertAndSend("/topic/messages/" + message.getChatRoomId(), message);
    }
}

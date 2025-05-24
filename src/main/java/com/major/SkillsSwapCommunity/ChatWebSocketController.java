package com.major.SkillsSwapCommunity;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Controller
public class ChatWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private chatService ChatService;


    @MessageMapping("/chat.sendMessage") // listen to /app/chat.sendMessage
    public void sendMessage(@Payload ChatMessage message) {
//        System.out.println("Received message: " + message);
        ChatService.saveMessages(message);

        // Send message to all subscribed clients of this chatRoomId
        messagingTemplate.convertAndSend("/topic/messages/" + message.getChatRoomId(), message);
    }
}

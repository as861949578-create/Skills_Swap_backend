package com.major.SkillsSwapCommunity;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.service.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;


public class ChatWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private chatService ChatService;



    @MessageMapping("/chat.sendMessage") // POST from /app/chat.sendMessage
    public void sendMessage(@Payload ChatMessage message) {

        ChatService.saveMessages(message);

        messagingTemplate.convertAndSend("/topic/messages/" + message.getChatRoomId(), message);
    }
}

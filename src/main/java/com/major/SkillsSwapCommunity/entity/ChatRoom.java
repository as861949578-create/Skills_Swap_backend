package com.major.SkillsSwapCommunity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "chatrooms")
public class ChatRoom {
    @Id
    private String id;
    private String user1Id;
    private String user2Id;
    private String swapRequestId;
    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.major.SkillsSwapCommunity.dto;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatThreadDto {
    private String chatRoomId;
    private UserDetails user;
    private String skillset;
    private List<ChatMessage> messages; // Optional for full thread view
}

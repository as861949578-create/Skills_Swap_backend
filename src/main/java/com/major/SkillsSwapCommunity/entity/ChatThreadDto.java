package com.major.SkillsSwapCommunity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatThreadDto {
    private UserDetails user1;
    private UserDetails user2;

}

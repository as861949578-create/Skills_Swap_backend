package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface chatMessageRepo extends MongoRepository<ChatMessage,String> {

    List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(String chatRoomId);
}

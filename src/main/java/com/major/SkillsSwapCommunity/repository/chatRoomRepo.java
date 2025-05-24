package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface chatRoomRepo extends MongoRepository<ChatRoom,String> {

}

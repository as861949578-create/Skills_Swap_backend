package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface chatRoomRepo extends MongoRepository<ChatRoom,String> {

    List<ChatRoom> findByUser1IdOrUser2Id(String user1Id, String user2Id);

}

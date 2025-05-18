package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.swapRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface swapRequestsRepo extends MongoRepository<swapRequest,ObjectId> {
    List<swapRequest> findByreceiverID(String email);
    List<swapRequest> findBysenderID(String email);
//    List<swapRequest> findByReceiverID(String receiverID);

}

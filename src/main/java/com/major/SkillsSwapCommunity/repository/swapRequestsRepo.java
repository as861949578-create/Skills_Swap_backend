package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.swapRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface swapRequestsRepo extends MongoRepository<swapRequest,ObjectId> {
    List<swapRequest> findByReceiverIDOrderByCreatedAtDesc(String email);
    List<swapRequest> findBySenderIDOrderByCreatedAtDesc(String email);
//    List<swapRequest> findByReceiverID(String receiverID);

    Optional<swapRequest> findFirstBySenderIDAndReceiverIDAndRequestedSkillAndStatusIn(
            String senderID, String receiverID, String skill, List<String> statuses);

}

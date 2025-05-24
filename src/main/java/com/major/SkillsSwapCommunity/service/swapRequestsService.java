package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.swapRequest;
import com.major.SkillsSwapCommunity.repository.swapRequestsRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class swapRequestsService {

    @Autowired
    private swapRequestsRepo SwapRequestRepo;



    public void saveSwapRequests(swapRequest SwapRequest){
        SwapRequestRepo.save(SwapRequest);
    }


    public List<swapRequest> findAllreceiver(String email) {
       return SwapRequestRepo.findByReceiverIDOrderByCreatedAtDesc(email);
    }

    public List<swapRequest> findAllsender(String email) {
        return SwapRequestRepo. findBySenderIDOrderByCreatedAtDesc(email);
    }

    public Optional<swapRequest> findbyId(String requestId) {
        ObjectId id = new ObjectId(requestId);
       return SwapRequestRepo.findById(id);
    }

    public Optional<swapRequest> findBySenderReceiverAndSkill(String senderId, String receiverId, String skill) {
        return SwapRequestRepo.findFirstBySenderIDAndReceiverIDAndRequestedSkillAndStatusIn(
                senderId, receiverId, skill, List.of("Pending", "Accepted")
        );
    }

}

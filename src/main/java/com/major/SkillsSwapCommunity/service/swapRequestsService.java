package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.swapRequest;
import com.major.SkillsSwapCommunity.repository.swapRequestsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class swapRequestsService {

    @Autowired
    private swapRequestsRepo SwapRequestRepo;



    public void saveSwapRequests(swapRequest SwapRequest){
        SwapRequestRepo.save(SwapRequest);
    }


    public List<swapRequest> findAll(String email) {
       return SwapRequestRepo.findByReceiverID(email);
    }
}

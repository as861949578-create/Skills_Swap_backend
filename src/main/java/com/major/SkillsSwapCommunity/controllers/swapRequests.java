package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.swapRequest;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.service.swapRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/skill-swap")
public class swapRequests {

    @Autowired
    private swapRequestsService SwapRequestService;

    @Autowired
    private jwtUtils JwtUtils;

    // receive --> sId, rID,msg,reqSkill
    @PostMapping("/request")
    public ResponseEntity<?> swapRequest(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestBody swapRequest swapDetails){
        //save these into db collection
         try{
             if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                         .body(new ApiResponse<>(false, "Missing token", null));
             }
             String token = tokenHeader.substring(7);
             String email = JwtUtils.extractEmail(token);

             //set email as senderID from jwt token
             swapDetails.setSenderID(email);

             if (swapDetails.getStatus() == null) {
                 swapDetails.setStatus("pending");
             }
             if (swapDetails.getCreatedAt() == null) {
                 swapDetails.setCreatedAt(LocalDateTime.now());
             }
             if(swapDetails.getOfferedSkill() == null){
                 swapDetails.setOfferedSkill("");
             }
             swapDetails.setUpdatedAt(LocalDateTime.now());
             // save it into db -> controller ->service ->repo
             SwapRequestService.saveSwapRequests(swapDetails);

             return ResponseEntity.ok(new ApiResponse<>(true,"Swap request saved successfully!",null));

         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(new ApiResponse<>(false,"Failed to save swap request",e.getMessage()));
         }
    }

    //request -> token ->extract id ->find user(in receiver field)

}

package com.major.SkillsSwapCommunity.controllers;


import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.SwapRequestCardDTO;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.entity.swapRequest;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.service.authService;
import com.major.SkillsSwapCommunity.service.swapRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.lang.String;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/skill-swap")
public class swapRequests {
    @Autowired
    private authService AuthService;
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


             // Check if a similar request already exists
             Optional<swapRequest> existingRequest = SwapRequestService.findBySenderReceiverAndSkill(
                     swapDetails.getSenderID(), swapDetails.getReceiverID(), swapDetails.getRequestedSkill()
             );

             if (existingRequest.isPresent()) {
                 return ResponseEntity.ok(new ApiResponse<>(false, "Duplicate request: You already sent a request for this skill.", null));
             }


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


    @GetMapping("/get-all-request")
    public ResponseEntity<?> GetAllRequest( @RequestHeader("Authorization") String tokenHeader)
    {
       try{
           if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(new ApiResponse<>(false, "Missing token", null));
           }
           String token = tokenHeader.substring(7);
           String email = JwtUtils.extractEmail(token);

           List<SwapRequestCardDTO> response = new ArrayList<>();

           List<swapRequest> receivedRequests = SwapRequestService.findAllreceiver(email);
           List<swapRequest> sentRequests = SwapRequestService.findAllsender(email);

//           System.out.println(receivedRequests);
//           System.out.println(sentRequests);

           List<swapRequest> allRequests = new ArrayList<>();
           allRequests.addAll(receivedRequests);
           allRequests.addAll(sentRequests);
           for(var request : allRequests)
           {

               Optional<UserDetails> sender = AuthService.findbymail(request.getSenderID());
               Optional<UserDetails> receiver = AuthService.findbymail(request.getReceiverID());
               if (sender.isPresent() && receiver.isPresent()) {
                   response.add(new SwapRequestCardDTO(request, sender.get(), receiver.get()));
               }
           }
           return ResponseEntity.ok(new ApiResponse<>(true,"succesfully fetched requests",response));
       }catch(Exception e)
       {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse<>(false,"Failed to fetched",e.getMessage()));

       }
    }



    @GetMapping("/sent-requests")
    public ResponseEntity<?> GetAllSentRequest( @RequestHeader("Authorization") String tokenHeader)
    {
        try{
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Missing token", null));
            }
            String token = tokenHeader.substring(7);
            String email = JwtUtils.extractEmail(token);

            List<SwapRequestCardDTO> response = new ArrayList<>();
            List<swapRequest> sentRequests = SwapRequestService.findAllsender(email);
            for(var request : sentRequests)
            {

                Optional<UserDetails> sender = AuthService.findbymail(request.getSenderID());
                Optional<UserDetails> receiver = AuthService.findbymail(request.getReceiverID());
                if (sender.isPresent() && receiver.isPresent()) {
                    response.add(new SwapRequestCardDTO(request, sender.get(), receiver.get()));
                }
            }
            return ResponseEntity.ok(new ApiResponse<>(true,"succesfully fetched all send requests",response));
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"Failed to fetched send requests",e.getMessage()));

        }
    }

    @GetMapping("/received-requests")
    public ResponseEntity<?> GetAllReceivedRequest( @RequestHeader("Authorization") String tokenHeader)
    {
        try{
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Missing token", null));
            }
            String token = tokenHeader.substring(7);
            String email = JwtUtils.extractEmail(token);

            List<SwapRequestCardDTO> response = new ArrayList<>();
            List<swapRequest> receivedRequests = SwapRequestService.findAllreceiver(email);
            for(var request : receivedRequests)
            {

                Optional<UserDetails> sender = AuthService.findbymail(request.getSenderID());
                Optional<UserDetails> receiver = AuthService.findbymail(request.getReceiverID());
                if (sender.isPresent() && receiver.isPresent()) {
                    response.add(new SwapRequestCardDTO(request, sender.get(), receiver.get()));
                }
            }
            return ResponseEntity.ok(new ApiResponse<>(true,"succesfully fetched all received requests",response));
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"Failed to fetched received requests",e.getMessage()));

        }
    }
   @PutMapping("/update-request/{id}")
    public ResponseEntity<?> updateSwapRequest(@PathVariable("id") String requestId ,
                                               @RequestBody Map<String , Object > reqBody,
                                               @RequestHeader("Authorization") String tokenHeader
   )
   {
       try{

           if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(new ApiResponse<>(false, "Missing token", null));
           }
           Boolean status = Boolean.parseBoolean(reqBody.get("status").toString());
           Object offeredObj = reqBody.get("offeredSkill");
           String offeredSkill =(offeredObj != null) ? offeredObj.toString() : "";
           Optional<swapRequest> optionalRequest = SwapRequestService.findbyId(requestId);
           if (optionalRequest.isEmpty()) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(new ApiResponse<>(false, "No swap request present in db", null));
           }

           swapRequest request = optionalRequest.get();
            if(status)
           {

              request.setOfferedSkill(offeredSkill);
              request.setUpdatedAt(LocalDateTime.now());
              request.setStatus("Accepted");
               SwapRequestService.saveSwapRequests(request);

           }else if(!status)
           {
               request.setUpdatedAt(LocalDateTime.now());
               request.setStatus("Rejected");
               SwapRequestService.saveSwapRequests(request);
           }
           return ResponseEntity.ok(new ApiResponse<>(true,"succesfully updated request",request));


       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse<>(false,"Failed to update request",e.getMessage()));
       }
   }
}

package com.major.SkillsSwapCommunity.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "SkillSwapRequests")
@Data
public class swapRequest {

    @Id
    private String id;

    private String senderID = "example@gmail.com";
    private String receiverID;
    private String requestedSkill ;
    private String offeredSkill = null;
    private String message;
    private String status = "Pending";

    private LocalDateTime createdAt = LocalDateTime.now() ;
    private LocalDateTime updatedAt = LocalDateTime.now();



}

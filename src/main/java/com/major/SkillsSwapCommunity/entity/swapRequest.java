package com.major.SkillsSwapCommunity.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "SkillSwapRequests")
public class swapRequest {

    @Id
    private ObjectId id;

    private String senderID = "example@gmail.com";
    private String receiverID;
    private String requestedSkill;
    private String offeredSkill = null;
    private String message;
    private String status = "pending";

    private LocalDateTime createdAt = LocalDateTime.now() ;
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getRequestedSkill() {
        return requestedSkill;
    }

    public void setRequestedSkill(String requestedSkill) {
        this.requestedSkill = requestedSkill;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getOfferedSkill() {
        return offeredSkill;
    }

    public void setOfferedSkill(String offeredSkill) {
        this.offeredSkill = offeredSkill;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public swapRequest(ObjectId id, String senderID, String receiverID, String requestedSkill, String offeredSkill, String message, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.requestedSkill = requestedSkill;
        this.offeredSkill = offeredSkill;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



}

package com.major.SkillsSwapCommunity.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

//@Component
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class SwapRequestCardDTO {

    private swapRequest request;
    private UserDetails senderDetails;
    private UserDetails receiverDetails;

    public swapRequest getRequest() {
        return request;
    }

    public void setRequest(swapRequest request) {
        this.request = request;
    }

    public UserDetails getSenderDetails() {
        return senderDetails;
    }

    public void setSenderDetails(UserDetails senderDetails) {
        this.senderDetails = senderDetails;
    }

    public UserDetails getReceiverDetails() {
        return receiverDetails;
    }

    public void setReceiverDetails(UserDetails receiverDetails) {
        this.receiverDetails = receiverDetails;
    }

    public SwapRequestCardDTO(swapRequest request, UserDetails senderDetails, UserDetails receiverDetails) {
        this.request = request;
        this.senderDetails = senderDetails;
        this.receiverDetails = receiverDetails;
    }




}

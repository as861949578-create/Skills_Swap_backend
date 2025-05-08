package com.major.SkillsSwapCommunity.entity;

public class OtpRequest {

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    private UserDetails userDetails;

    public OtpRequest(UserDetails userDetails, int otp) {
        this.userDetails = userDetails;
        this.otp = otp;
    }

    private int otp;
}

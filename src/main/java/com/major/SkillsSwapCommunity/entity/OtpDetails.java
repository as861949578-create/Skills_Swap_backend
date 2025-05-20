package com.major.SkillsSwapCommunity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpDetails {

    private int otp;
    private long timestamp;
}

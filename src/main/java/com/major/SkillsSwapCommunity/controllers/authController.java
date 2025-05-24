package com.major.SkillsSwapCommunity.controllers;

import com.major.SkillsSwapCommunity.entity.*;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.service.authService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class authController {



  @Autowired
  private jwtUtils JwtUtils;
  @Autowired
  private authService AuthService;

  // call this Api when user click for Otp to verify his/her email
  @PostMapping("/signup-to-otp")
  public ResponseEntity<?> signupToOtp(@RequestBody UserDetails signUpRequest){
    System.out.println("idahr ayaa");
    return AuthService.signupToOtp(signUpRequest);
  }


  // here comes otp
  @PostMapping("/verifyOtp")
  public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest otpRequest){
    System.out.println("step1" + otpRequest.getOtp());
    UserDetails signupRequest = otpRequest.getUserDetails(); // null check zaruri
    if (signupRequest == null) {
      return ResponseEntity.ok(new ApiResponse<>(false,("User details is missing"),null));
    }
    return AuthService.verifyOtp(otpRequest.getOtp(), otpRequest.getUserDetails());
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody loginDto loginRequest){

    if(loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()){

      return ResponseEntity.ok(new ApiResponse<>(false,"Please enter your Credentials first",null));
    }
    return AuthService.login(loginRequest);
  }


}

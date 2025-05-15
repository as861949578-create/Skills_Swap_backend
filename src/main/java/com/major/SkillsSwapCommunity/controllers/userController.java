package com.major.SkillsSwapCommunity.controllers;

import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.entity.userDetailsUpdate;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.passwordUtils.passwordUtils;
import com.major.SkillsSwapCommunity.service.userService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class userController {

    @Autowired
    private userService UserService;

    @Autowired
    private passwordUtils Passwordutils;

    @Autowired
    private jwtUtils JwtUtils;




    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String tokenHeader) {

        return UserService.check(tokenHeader);
    }

    @PutMapping("/update")
    public ResponseEntity<?> userProfileUpdate(@RequestHeader("Authorization") String tokenHeader, @RequestBody userDetailsUpdate updatedUser) {
        System.out.println("updated user details " + updatedUser);
        ResponseEntity<?> checkResponse = UserService.check(tokenHeader);
        HttpStatus status = (HttpStatus) checkResponse.getStatusCode();
        String message = (String) checkResponse.getBody();

        if (status == HttpStatus.BAD_REQUEST || status == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.ok(new ApiResponse<>(false,message,null));
        }
        System.out.println("step1");

        @SuppressWarnings("unchecked")
        Optional<UserDetails> optionalUser = (Optional<UserDetails>) checkResponse.getBody();


        if (optionalUser.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(false,"user not found",null));
        }

        UserDetails existingUser = (UserDetails) optionalUser.get();

        existingUser.setName(updatedUser.getName());
        existingUser.setSkills(updatedUser.getSkills());
        existingUser.setContact(updatedUser.getContact());

        UserService.save(existingUser);
     return ResponseEntity.ok(new ApiResponse<>(true,"update ho gaya ! Heera Bete",existingUser));

    }
}


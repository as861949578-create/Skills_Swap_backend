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
    public ResponseEntity<ApiResponse<UserDetails>> userProfileUpdate(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestBody userDetailsUpdate updatedUser) {

//        System.out.println("Received update request for user: " + updatedUser);

        ResponseEntity<ApiResponse<Optional<UserDetails>>> checkResponse = UserService.check(tokenHeader);

        // Step 1: Validate token and extract user info
        HttpStatus status = (HttpStatus) checkResponse.getStatusCode();
        ApiResponse<Optional<UserDetails>> responseBody = checkResponse.getBody();

        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Unexpected error", null));
        }

        if (status == HttpStatus.BAD_REQUEST || status == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(status)
                    .body(new ApiResponse<>(false, responseBody.getMessage(), null));
        }

        Optional<UserDetails> optionalUser = responseBody.getData();

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User not found", null));
        }

        // Step 2: Update the fields
        UserDetails existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setSkills(updatedUser.getSkills());
        existingUser.setContact(updatedUser.getContact());

        // Step 3: Save the user
        UserService.save(existingUser);

        return ResponseEntity.ok(new ApiResponse<>(true, "Update successful", existingUser));
    }

}


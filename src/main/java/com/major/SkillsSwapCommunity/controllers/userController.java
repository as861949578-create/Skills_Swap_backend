package com.major.SkillsSwapCommunity.controllers;

import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.entity.userDetailsUpdate;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.passwordUtils.passwordUtils;
import com.major.SkillsSwapCommunity.service.userService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        System.out.println("token issss " + tokenHeader);
        return UserService.check(tokenHeader);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserDetails>> userProfileUpdate(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestBody userDetailsUpdate updatedUser) {

        // Step 1: Validate token and extract user info
        ResponseEntity<ApiResponse<UserDetails>> checkResponse = UserService.check(tokenHeader);

        HttpStatus status = (HttpStatus) checkResponse.getStatusCode();
        ApiResponse<UserDetails> responseBody = checkResponse.getBody();

        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Unexpected error", null));
        }

        if (!responseBody.getSuccess() || responseBody.getData() == null) {
            return ResponseEntity.status(status)
                    .body(new ApiResponse<>(false, responseBody.getMessage(), null));
        }

        // Step 2: Update the fields
        UserDetails existingUser = responseBody.getData();
        existingUser.setName(updatedUser.getName());
        existingUser.setSkills(updatedUser.getSkills());
        existingUser.setContact(updatedUser.getContact());

        // Step 3: Save the user
        UserService.save(existingUser);

        return ResponseEntity.ok(new ApiResponse<>(true, "Update successful", existingUser));
    }


    @GetMapping("/get-someone-profile")
    public ResponseEntity<?> getSomeoneProfile(@RequestParam String email){
        if(email.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new ApiResponse<>(false,"Email nahi aayi",null));
        }

        Optional<UserDetails> user = UserService.findByEmail(email);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new ApiResponse<>(false,"Email farzi hai, user nahi mila",null));
        }
        else{
             UserDetails userDetailsWithoutPassword = user.get();
             userDetailsWithoutPassword.setPassword(null);
            return ResponseEntity.ok(new ApiResponse<>(true,"user mil gaya",userDetailsWithoutPassword));
        }
    }
}


package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.repository.authRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class userService {
    @Autowired
    private authRepo AuthRepo;

    @Autowired
    private jwtUtils JwtUtils;

    public Optional<UserDetails> findByEmail(String email){
        return AuthRepo.findByEmailIgnoreCase(email);
    }
    public void save(UserDetails User){
        AuthRepo.save(User);
    }

    public ResponseEntity<ApiResponse<UserDetails>> check(String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
//            System.out.println("yaha aayaa..");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Missing token", null));
        }

        String token = tokenHeader.substring(7);
        String email = JwtUtils.extractEmail(token);

        if (!JwtUtils.validateTokenWithEmail(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Token is invalid or expired", null));
        }

//        String email = JwtUtils.extractEmail(token);
        Optional<UserDetails> currentUserDetails = findByEmail(email);

        if(currentUserDetails.isPresent()) {
            UserDetails userWithoutPassword = currentUserDetails.get();
            userWithoutPassword.setPassword(null);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>(true, "User found", userWithoutPassword));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "User not found", null));
        }
    }
}

package com.major.SkillsSwapCommunity.service;

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
//    public Optional<UserDetails>findById(ObjectId id){
//        return AuthRepo.findById(id);
//    }
    public void save(UserDetails User){
        AuthRepo.save(User);
    }

    public ResponseEntity<?> check(String tokenHeader) {

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
//            System.out.println("step1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing token");
        }
        String token = tokenHeader.substring(7);
        if (JwtUtils.validateToken(token)) {
            System.out.println("step2");
            String email = JwtUtils.extractEmail(token);
            Optional<UserDetails> currentUserDetails = findByEmail(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(currentUserDetails);
        }
//        System.out.println("step3");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is null or invalidate");
    }
}

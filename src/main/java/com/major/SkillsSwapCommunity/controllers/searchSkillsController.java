package com.major.SkillsSwapCommunity.controllers;

import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.service.skillsSearchService;
import com.major.SkillsSwapCommunity.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class searchSkillsController {

    @Autowired
    private skillsSearchService SkillsSearchService;

    @Autowired
//    private userService UserService;
    private jwtUtils JwtUtils;

    @GetMapping("/all-skill")
    public ResponseEntity<?> GetAllSkills() {
        List<String> skills = SkillsSearchService.getAllDistinctSkills();
        return ResponseEntity.ok(new ApiResponse<>(true, "Saari Skills aa chuki hai", skills));
    }

    @GetMapping("/search-user")
    public ResponseEntity<?> getAllUsersBySkill(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestParam(required = false) String skill) {

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Missing or invalid token", null));
        }

        String token = tokenHeader.substring(7);

        // Check token validity
        if (!JwtUtils.validateTokenWithEmail(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Invalid token", null));
        }

        String email = JwtUtils.extractEmail(token);

        List<UserDetails> users;
        if (skill != null && !skill.isBlank()) {
            users = SkillsSearchService.findBySkillsContaining(skill, email);
            return ResponseEntity.ok(new ApiResponse<>(true, "Saare skilled users mil gaye", users));
        } else {
            users = SkillsSearchService.getAllUsers(email);
            return ResponseEntity.ok(new ApiResponse<>(true, "All users mil gaye", users));
        }
    }

}

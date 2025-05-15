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
            @RequestParam String skill) {

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing token");
        }
        String token = tokenHeader.substring(7);
        String email = "";
        if (JwtUtils.validateToken(token) && skill != null) {
             email = JwtUtils.extractEmail(token);
            List<UserDetails> users = SkillsSearchService.findBySkillsContaining(skill, email);
            return ResponseEntity.ok(new ApiResponse<>(true, "Saare users aa chuke hai", users));
        }

        if (skill == null) {
            List<UserDetails> allUsers = SkillsSearchService.getAllUsers(email);
            return ResponseEntity.ok(new ApiResponse<>(true, "All Users Find Successfully", allUsers));
        }

        return ResponseEntity.ok(new ApiResponse<>(false, "token in not valid", null));

    }
}

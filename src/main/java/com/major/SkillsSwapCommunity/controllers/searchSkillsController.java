package com.major.SkillsSwapCommunity.controllers;

import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.service.skillsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class searchSkillsController {

    @Autowired
    private skillsSearchService SkillsSearchService;

    @GetMapping("/all-skill")
    public ResponseEntity<?> GetAllSkills(){
        List<String>skills = SkillsSearchService.getAllDistinctSkills();
        return ResponseEntity.ok(new ApiResponse<>(true,"Saari Skills aa chuki hai",skills));
    }

    @GetMapping("/search-user/{skill}")
    public ResponseEntity<?> getAllUsersBySkill(@PathVariable String skill) {
        if(skill == null){
          List<UserDetails> allUsers = SkillsSearchService.getAllUsers();
          return ResponseEntity.ok(new ApiResponse<>(true,"All Users Find Successfully",allUsers));
        }


        List<UserDetails> users = SkillsSearchService.findBySkillsContaining(skill);


        return ResponseEntity.ok(new ApiResponse<>(true,"Saare users aa chuke hai",users));
    }

}

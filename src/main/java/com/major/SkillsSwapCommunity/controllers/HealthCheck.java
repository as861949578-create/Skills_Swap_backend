package com.major.SkillsSwapCommunity.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HealthCheck
{

    @GetMapping("/health-check")
    public String HealthCheck(){
        System.out.println("hyy");
        return "OK";
    }
}

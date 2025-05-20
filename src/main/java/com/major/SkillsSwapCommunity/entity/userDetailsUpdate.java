package com.major.SkillsSwapCommunity.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class userDetailsUpdate {
   private String name;
    private List<String> skills = new ArrayList<>();
    private String contact;


    private String githubLink;
    private String linkedinLink;
    private String youtubeLink;
    private String instagramLink;
    private String bio;
    private String location;



    public userDetailsUpdate(String name,String contact, List<String> skills,
                             String location, String bio, String instagramLink, String youtubeLink, String linkedinLink, String githubLink) {
        this.name = name;
        this.skills = skills;
        this.contact = contact;
        this.location = location;
        this.bio = bio;
        this.instagramLink = instagramLink;
        this.youtubeLink = youtubeLink;
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
    }

}

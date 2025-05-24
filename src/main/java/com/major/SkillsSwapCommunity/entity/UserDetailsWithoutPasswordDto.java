package com.major.SkillsSwapCommunity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class UserDetailsWithoutPasswordDto {

    private String id;
    private String name;
    private String email;
    private String contact;
    private List<String> skills ;
    private String githubLink;
    private String linkedinLink;
    private String youtubeLink;
    private String instagramLink;
    private String bio;
    private String location;


    public UserDetailsWithoutPasswordDto(String name, String email, String contact, List<String> skills,
                                         String location, String bio, String instagramLink, String youtubeLink, String linkedinLink, String githubLink) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.skills = skills;
        this.location = location;
        this.bio = bio;
        this.instagramLink = instagramLink;
        this.youtubeLink = youtubeLink;
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
    }

    //no args cons
    public UserDetailsWithoutPasswordDto() {

    }
}

package com.major.SkillsSwapCommunity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class UserDetails {

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }
//
//    public List<String> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<String> skills) {
//        this.skills = skills;
//    }
//
//    public UserDetails(String id, String name, String email, String contact, List<String> skills,String password) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.contact = contact;
//        this.skills = skills;
//        this.password = password;
//    }
//
//    public UserDetails(String name, String email, String contact, List<String> skills,String password) {
//        this.name = name;
//        this.email = email;
//        this.contact = contact;
//        this.skills = skills;
//        this.password = password;
//    }

    private String id;
    private String name;
    private String email;
    private String contact;

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    private String password;
    private List<String> skills ;
    private String youtubeLink;
    private String location;
    private String instagramProfile;
    private String githubProfile;
    private String bio;
    //no args cons
//    public UserDetails() {
//
//    }
}

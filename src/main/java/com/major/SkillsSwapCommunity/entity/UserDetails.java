package com.major.SkillsSwapCommunity.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
public class UserDetails {

    private String id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private List<String> skills ;
    private String githubLink;
    private String linkedinLink;
    private String youtubeLink;
    private String instagramLink;
    private String bio;
    private String location;

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public UserDetails( String name, String email, String contact, List<String> skills,String password,
                       String location, String bio, String instagramLink, String youtubeLink, String linkedinLink, String githubLink) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.skills = skills;
        this.password = password;
        this.location = location;
        this.bio = bio;
        this.instagramLink = instagramLink;
        this.youtubeLink = youtubeLink;
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
    }

    public UserDetails(String id,String name, String email, String contact, List<String> skills,String password,
                       String location, String bio, String instagramLink, String youtubeLink, String linkedinLink, String githubLink) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.skills = skills;
        this.password = password;
        this.location = location;
        this.bio = bio;
        this.instagramLink = instagramLink;
        this.youtubeLink = youtubeLink;
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    //no args cons
    public UserDetails() {

    }
}

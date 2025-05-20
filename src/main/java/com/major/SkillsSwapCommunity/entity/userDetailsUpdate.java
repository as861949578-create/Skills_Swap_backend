package com.major.SkillsSwapCommunity.entity;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}

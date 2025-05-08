package com.major.SkillsSwapCommunity.entity;

import java.util.ArrayList;
import java.util.List;

public class userDetailsUpdate {
   private String name;

    public userDetailsUpdate(String name,String contact, List<String> skills) {
        this.name = name;
        this.skills = skills;
        this.contact =contact;
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

    private List<String> skills = new ArrayList<>();

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String contact;
}

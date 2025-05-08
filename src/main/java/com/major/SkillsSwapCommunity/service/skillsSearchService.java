package com.major.SkillsSwapCommunity.service;

import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.repository.skillsSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class skillsSearchService {



    @Autowired
    private skillsSearchRepo SkillsSearchRepo;

    public List<UserDetails> findBySkillsContaining(String skill){
        return  SkillsSearchRepo.findBySkillsContaining(skill);
    }

    public List<UserDetails>getAllUsers(){
        return SkillsSearchRepo.findAll();
    }
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<String> getAllDistinctSkills() {
        return mongoTemplate.findDistinct(new Query(),"skills","users",String.class);
    }

}

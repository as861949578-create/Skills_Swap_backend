package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface skillsSearchRepo extends MongoRepository<UserDetails, ObjectId> {
    @Query("{ 'skills': { $regex: ?0, $options: 'i' }, 'email': { $ne: ?1 } }")
    List<UserDetails> findBySkillsContainingAndEmailNot(String skill, String email);

    @Query("{ 'email': { $ne: ?0 } }")
    List<UserDetails> findAllExcludingEmail(String email);

}

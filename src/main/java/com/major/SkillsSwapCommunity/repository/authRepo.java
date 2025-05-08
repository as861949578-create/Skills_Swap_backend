package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.UserDetails;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface authRepo extends MongoRepository<UserDetails, ObjectId> {
     boolean existsByEmailIgnoreCase(String email);
     Optional<UserDetails> findByEmailIgnoreCase(String email);
}

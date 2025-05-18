package com.major.SkillsSwapCommunity.repository;

import com.major.SkillsSwapCommunity.entity.swapRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface swapRequestsRepo extends MongoRepository<swapRequest,ObjectId> {

}

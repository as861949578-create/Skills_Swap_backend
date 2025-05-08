package com.major.SkillsSwapCommunity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SkillsSwapCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillsSwapCommunityApplication.class, args);
	}


}

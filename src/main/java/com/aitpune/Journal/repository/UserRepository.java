package com.aitpune.Journal.repository;

import com.aitpune.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByusername(String username);
    User deleteByusername(String username);
}

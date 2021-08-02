package com.slcinema.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.slcinema.models.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    public User findByUsername(String username);
}

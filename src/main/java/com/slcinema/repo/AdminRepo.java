package com.slcinema.repo;

import com.slcinema.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends MongoRepository<Admin,String> {
    public Admin findByUsername(String username);

}

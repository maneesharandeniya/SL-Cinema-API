package com.slcinema.repo;

import com.slcinema.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends MongoRepository<Admin,String> {
    public Admin findByEmail(String email);
    void deleteByUsername(String username);
}

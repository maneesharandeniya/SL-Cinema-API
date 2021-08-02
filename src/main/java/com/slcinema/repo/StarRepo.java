package com.slcinema.repo;

import com.slcinema.models.Star;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StarRepo extends MongoRepository<Star,String> {
}

package com.slcinema.repo;

import com.slcinema.models.CinemaStar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CinemaStarRepo extends MongoRepository<CinemaStar,String> {
}

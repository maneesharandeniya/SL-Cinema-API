package com.slcinema.repo;

import com.slcinema.models.CinemaStar;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CinemaStarRepo extends MongoRepository<CinemaStar,String> {
   // void delete(Optional<CinemaStar> star);
}

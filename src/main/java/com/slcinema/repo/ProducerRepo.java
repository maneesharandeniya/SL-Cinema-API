package com.slcinema.repo;

import com.slcinema.models.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepo extends MongoRepository<Producer,String> {
}

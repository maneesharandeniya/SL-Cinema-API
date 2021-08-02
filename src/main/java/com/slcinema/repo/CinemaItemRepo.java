package com.slcinema.repo;

import com.slcinema.models.CinemaItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaItemRepo extends MongoRepository<CinemaItem,String> {

    public List<CinemaItem> findByCategory(String category);
    public CinemaItem findByTitle(String title);
}

package com.slcinema.repo;

import com.slcinema.models.CinemaItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaItemRepo extends MongoRepository<CinemaItem,String> {

    public List<CinemaItem> findByCategory(String category);
    public CinemaItem findByTitle(String title);
    void delete(Optional<CinemaItem> cinemaItem);
}

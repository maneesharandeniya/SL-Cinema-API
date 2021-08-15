package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.models.CinemaStar;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.repo.CinemaStarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    CinemaItemRepo cinemaItemRepo;

    @Autowired
    private CinemaStarRepo cinemaStarRepo;

    @GetMapping(value = "find/title/{title}" ,produces = {"application/json"})
    public Optional<CinemaItem> getCinemaItemByTitle(@PathVariable("title") String title){
        Optional<CinemaItem> cinemaItem = Optional.ofNullable(cinemaItemRepo.findByTitle(title));
        return cinemaItem;
    }

    @GetMapping(value = "find/{id}")
    public Optional<CinemaItem> getCinemaIteById(@PathVariable("id") String id){
        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);
        return cinemaItem;
    }

    @GetMapping(value = "star/{id}")
    public Optional<CinemaStar> getCinemaStar(@PathVariable("id") String id){
        Optional<CinemaStar> cinemaStar = cinemaStarRepo.findById(id);
        return cinemaStar;
    }

    @GetMapping("/stars/all")
    public List<CinemaStar> getStars(){
        List<CinemaStar> stars = cinemaStarRepo.findAll();
        return stars;
    }

    @GetMapping("/movies/all")
    public List<CinemaItem> getMovies(){
        List<CinemaItem> movies = cinemaItemRepo.findByCategory("movie");
        return movies;
    }

    @GetMapping("/teledrama/all")
    public List<CinemaItem> getTeledramas(){
        List<CinemaItem> teledrama = (List<CinemaItem>) cinemaItemRepo.findByCategory("teledrama");
        return teledrama;
    }

    @GetMapping("/web-series/all")
    public List<CinemaItem> getWebSeries(){
        List<CinemaItem> webSeries = (List<CinemaItem>) cinemaItemRepo.findByCategory("web-series");
        return webSeries;
    }

    @GetMapping("/mini-series/all")
    public List<CinemaItem> getMiniSeries(){
        List<CinemaItem> miniSeries = (List<CinemaItem>) cinemaItemRepo.findByCategory("mini-series");
        return miniSeries;
    }

    @GetMapping("/short-movies/all")
    public List<CinemaItem> getShortMovies(){
        List<CinemaItem> shortMovies = (List<CinemaItem>) cinemaItemRepo.findByCategory("short-movie");
        return shortMovies;
    }

}

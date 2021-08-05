package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.services.CinemaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class CinemaItemController {

    @Autowired
    CinemaItemRepo cinemaItemRepo;

    @Autowired
    private CinemaItemService cinemaItemService;

    @GetMapping(value = "find/{title}" ,produces = {"application/json"})
    public CinemaItem getCinemaItemByTitle(@PathVariable("title") String title){
        CinemaItem cinemaItem = cinemaItemRepo.findByTitle(title);
        return cinemaItem;
    }

    @GetMapping(value = "find/{id}")
    public Optional<CinemaItem> getCinemaIteById(@PathVariable("id") String id){
        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);
        return cinemaItem;
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

package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.repo.CinemaItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CinemaItemRepo cinemaItemRepo;

    @PostMapping(value = "/cinema/add/item", consumes = {"application/json"})
    public String addNewCinemaItem(@Valid @RequestBody CinemaItem cinemaItem){
        cinemaItemRepo.save(cinemaItem);
        return "success";
    }
}

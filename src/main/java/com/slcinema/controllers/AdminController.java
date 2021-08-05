package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.models.CinemaStar;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.services.AdminService;
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

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/editor/cinema/add/item", consumes = {"application/json"})
    public String addNewCinemaItem(@Valid @RequestBody CinemaItem cinemaItem){
        String addConfirm = adminService.addNewItem(cinemaItem);
        return addConfirm;
    }

    @PostMapping(value = "/editor/cinema/add/star", consumes = {"application/json"})
    public String addNewCinemaStar(@Valid @RequestBody CinemaStar cinemaStar){
        String addConfirm = adminService.addNewStar(cinemaStar);
        return addConfirm;
    }
}

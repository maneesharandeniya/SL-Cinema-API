package com.slcinema.controllers;

import com.slcinema.models.Admin;
import com.slcinema.models.CinemaItem;
import com.slcinema.models.CinemaStar;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/editor/cinema/edit/item", consumes = {"application/json"})
    public String editCinemaItem(@Valid @RequestBody CinemaItem cinemaItem){
        String editConfirm = adminService.editItem(cinemaItem);
        return editConfirm;
    }

    @DeleteMapping(value = "/cinema/delete/item/{id}")
    public String deleteCinemaItem(@PathVariable("id") String id){
        String deleteConfirm = adminService.deleteItem(id);
        return deleteConfirm;
    }

    @PostMapping(value = "/editor/cinema/add/star", consumes = {"application/json"})
    public String addNewCinemaStar(@Valid @RequestBody CinemaStar cinemaStar){
        String addConfirm = adminService.addNewStar(cinemaStar);
        return addConfirm;
    }

    @PutMapping(value = "/editor/cinema/edit/star", consumes = {"application/json"})
    public String editCinemaStar(@Valid @RequestBody CinemaStar cinemaStar){
        String editConfirm = adminService.editStar(cinemaStar);
        return editConfirm;
    }

    @DeleteMapping(value = "/cinema/edit/star/{id}")
    public String deleteCinemaStar(@PathVariable("id") String id){
        String deleteConfirm = adminService.deleteStar(id);
        return deleteConfirm;
    }

    @PostMapping(value = "/add/editor" )
    public String addNewEditor(@Valid @RequestBody Admin editor){
        String addConfirm = adminService.addNewEditor(editor);
        return addConfirm;
    }
}

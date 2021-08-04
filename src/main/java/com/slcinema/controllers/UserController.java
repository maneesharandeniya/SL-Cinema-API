package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.models.ReviewObj;
import com.slcinema.models.User;
import com.slcinema.repo.UserRepo;
import com.slcinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signup", consumes = {"application/json"})
    public String userRegistration(@Valid @RequestBody User user){
        String regConfirm = userService.addNewUser(user);
        return regConfirm;
    }

    @GetMapping(value = "/cinema/rate")
    public String setRating(@RequestParam("id") String id, @RequestParam("rate")double rate){
        String rateConfirm = userService.rateMovieItem(id,rate);
        return  rateConfirm;
    }

    @GetMapping(value = "/cinema/wish-list/add")
    public AtomicReference<String> addToWishList(@RequestParam(name = "id") String id){
        AtomicReference<String> addWLConfirm = userService.addMovieItemToWishList(id);
        return addWLConfirm;
    }

    @GetMapping(value = "/cinema/get/wish-list", produces = {"application/json"})
    public List<CinemaItem> getMyWishList(){
        List<CinemaItem> wishList = userService.getMyWishList();
        return wishList;
    }

    @PostMapping(value = "/cinema/review", consumes = {"application/json"})
    public String setReview(@RequestBody ReviewObj reviewObj){
        String reviewConfirm = userService.reviewMovieItem(reviewObj.getId(),reviewObj.getReview());
        return  reviewConfirm;
    }

}

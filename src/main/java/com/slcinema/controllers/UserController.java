package com.slcinema.controllers;

import com.slcinema.models.CinemaItem;
import com.slcinema.models.ReviewObj;
import com.slcinema.models.User;
import com.slcinema.repo.UserRepo;
import com.slcinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String processRegister(@Valid @RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userService.register(user, getSiteURL(request));
        return "successfully registered";
    }
     /*
    @PostMapping(value = "/signup", consumes = {"application/json"})
    public String userRegistration(@Valid @RequestBody User user){
        String regConfirm = userService.addNewUser(user);
        return regConfirm;
    }*/

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public ModelAndView verifyUser(@RequestParam("code") String code) {
        if (userService.verify(code)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("succesful");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("unsuccesful");
            return modelAndView;
        }
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

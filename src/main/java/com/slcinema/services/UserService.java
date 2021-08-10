package com.slcinema.services;

import com.slcinema.controllers.JwtAuthenticationController;
import com.slcinema.models.CinemaItem;
import com.slcinema.models.User;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CinemaItemRepo cinemaItemRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String addNewUser(User user){
        User newUser = userRepo.findByEmail(user.getEmail());
        if(newUser != null){
            return "UserEmail already exist";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "successfully registered";
    }

    public String rateMovieItem(String id, double rate){
        System.out.println("Cinema Item rating service");

        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);
        String username = JwtAuthenticationController.getUserFromSession();
        System.out.println(username);
        if(username == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        if(!cinemaItem.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Item Not Found");
        }

        cinemaItem.ifPresent(item -> {
            User user = userRepo.findByEmail(username);
            ArrayList<String> ratedList = user.getRatedList();

            HashMap<String,Double> rateMap = item.getRateMap();
            if(rateMap == null){
                rateMap = new HashMap<String,Double>();
            }

            if(ratedList == null){
                ratedList = new ArrayList<String>();
            }
            if(ratedList.contains(item.getId())){
                ratedList.remove(item.getId());
                double curRate = item.getRatings()*item.getRatedCount();
                double prevRate = rateMap.get(user.getUsername());
                double newRate =  (curRate+rate-prevRate)/item.getRatedCount();
                item.setRatings(newRate);
            }else{
                double curRate = item.getRatings()*item.getRatedCount();
                item.setRatedCount(item.getRatedCount()+1);
                double newRate =  (curRate+rate)/item.getRatedCount();
                item.setRatings(newRate);
            }
            ratedList.add(item.getId());
            user.setRatedList(ratedList);

            rateMap.put(user.getUsername(), rate);
            item.setRateMap(rateMap);

            cinemaItemRepo.save(item);
            userRepo.save(user);
        });
        return "successfully rated";
    }

    public AtomicReference<String> addMovieItemToWishList(String id) {
        System.out.println("Cinema Item added to wishlist service");

        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);
        String username = JwtAuthenticationController.getUserFromSession();
        AtomicReference<String> message = new AtomicReference<>("");

        if(username == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        if(!cinemaItem.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Item Not Found");
        }
        cinemaItem.ifPresent(item -> {
            User user = userRepo.findByEmail(username);
            ArrayList<String> wishList = user.getWishlist();
            if(wishList == null){
                wishList = new ArrayList<String>();
            }
            if(wishList.contains(item.getId())){
                wishList.remove(item.getId());
                message.set("item removed from watch list");
            }else {
                wishList.add(item.getId());
                message.set("successfully added to wishlist");
            }
            user.setWishlist(wishList);
            userRepo.save(user);
        });
        return message;
    }

    public List<CinemaItem> getMyWishList() {
        System.out.println("get all Cinema Item from wishlist service");
        List<CinemaItem> items = new ArrayList<>();
        String username = JwtAuthenticationController.getUserFromSession();

        if(username == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        User user = userRepo.findByEmail(username);

        ArrayList<String> itemIDs = user.getWishlist();
        if(itemIDs != null){
            for(String itemID: itemIDs){
                Optional<CinemaItem> item = cinemaItemRepo.findById(itemID);
                item.ifPresent(i ->{
                    items.add(i);
                });
            }
        }
        return items;
    }

    public String reviewMovieItem(String id, String review) {
        System.out.println("Review Cinema Item service");

        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);
        String username = JwtAuthenticationController.getUserFromSession();

        if(username == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        if(!cinemaItem.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Item Not Found");
        }

        cinemaItem.ifPresent(item -> {
            User user = userRepo.findByEmail(username);
            ArrayList<String> reviewedItems = user.getReviewedList();
            if(reviewedItems == null){
                reviewedItems = new ArrayList<String>();
            }
            if(reviewedItems.contains(item.getId())){
                reviewedItems.remove(item.getId());
            }
            reviewedItems.add(item.getId());
            user.setReviewedList(reviewedItems);

            HashMap<String,String> reviews = item.getReviews();
            if(reviews == null){
                reviews = new HashMap<String,String>();
            }
            reviews.put(user.getUsername(),review);
            item.setReviews(reviews);

            cinemaItemRepo.save(item);
            userRepo.save(user);
        });
        return "successfully added review";
    }
}

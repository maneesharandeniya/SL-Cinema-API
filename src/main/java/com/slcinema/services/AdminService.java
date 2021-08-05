package com.slcinema.services;

import com.slcinema.controllers.JwtAuthenticationController;
import com.slcinema.models.CinemaItem;
import com.slcinema.models.CinemaStar;
import com.slcinema.repo.AdminRepo;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.repo.CinemaStarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CinemaItemRepo cinemaItemRepo;

    @Autowired
    private CinemaStarRepo cinemaStarRepo;

    public String addNewItem(CinemaItem cinemaItem){
        String adminName = JwtAuthenticationController.getUserFromSession();
        CinemaItem item = cinemaItemRepo.findByTitle(cinemaItem.getTitle());

        //TODO
        // get starID from cinema item and then set role imageUrl

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(item != null){
            throw new ResponseStatusException(
                    HttpStatus.FOUND, "Cinema Item Found");
        }
        cinemaItemRepo.save(cinemaItem);
        return "Successfully added item";
    }

    public String addNewStar(CinemaStar cinemaStar) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaItem> star = cinemaItemRepo.findById(cinemaStar.getName());

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(star.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.FOUND, "Cinema Star Found");
        }
        cinemaStarRepo.save(cinemaStar);
        return "Successfully added star";
    }
}

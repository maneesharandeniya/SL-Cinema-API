package com.slcinema.services;

import com.slcinema.controllers.JwtAuthenticationController;
import com.slcinema.exception.BadRequestException;
import com.slcinema.models.Admin;
import com.slcinema.models.CinemaItem;
import com.slcinema.models.CinemaStar;
import com.slcinema.models.Role;
import com.slcinema.repo.AdminRepo;
import com.slcinema.repo.CinemaItemRepo;
import com.slcinema.repo.CinemaStarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CinemaItemRepo cinemaItemRepo;

    @Autowired
    private CinemaStarRepo cinemaStarRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String addNewItem(CinemaItem cinemaItem){
        String adminName = JwtAuthenticationController.getUserFromSession();
        CinemaItem item = cinemaItemRepo.findByTitle(cinemaItem.getTitle());

        if(cinemaItem.getCast() != null) {
            ArrayList<Role> roles = cinemaItem.getCast();
            for(int i=0; i< roles.size(); i++){
                Optional<CinemaStar> star = cinemaStarRepo.findById(roles.get(i).getStarID());
                if(star == null){
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Cinema star Not Found");
                }
                int finalI = i;
                star.ifPresent(s -> {
                    roles.get(finalI).setImageUrl(s.getImageUrls().get(0));
                });
            }
        }
        if(cinemaItem.getProducers() != null) {
            ArrayList<Role> roles = cinemaItem.getProducers();
            for(int i=0; i< roles.size(); i++){
                Optional<CinemaStar> star = cinemaStarRepo.findById(roles.get(i).getStarID());
                if(star == null){
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Producer Not Found");
                }
                int finalI = i;
                star.ifPresent(s -> {
                    roles.get(finalI).setImageUrl(s.getImageUrls().get(0));
                });
            }
        }
        if(cinemaItem.getDirectors() != null) {
            ArrayList<Role> roles = cinemaItem.getDirectors();
            for(int i=0; i< roles.size(); i++){
                Optional<CinemaStar> star = cinemaStarRepo.findById(roles.get(i).getStarID());
                if(star == null){
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Director Not Found");
                }
                int finalI = i;
                star.ifPresent(s -> {
                    roles.get(finalI).setImageUrl(s.getImageUrls().get(0));
                });
            }
        }

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(item != null){
            throw new BadRequestException("Email address already in use.");
        }
        cinemaItemRepo.save(cinemaItem);
        return "Successfully added item";
    }

    public String editItem(CinemaItem cinemaItem){
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaItem> item = cinemaItemRepo.findById(cinemaItem.getId());

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(item == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Item Not Found");
        }
         item.ifPresent(i-> {
            cinemaItem.setRatings(i.getRatings());
            if(i.getRateMap() != null){
                cinemaItem.setRateMap(i.getRateMap());
            }
            cinemaItem.setRatedCount(i.getRatedCount());
            if(i.getReviews() != null){
                cinemaItem.setReviews(i.getReviews());
            }
            cinemaItemRepo.save(cinemaItem);
        });
        return "Successfully edited item";
    }

    public String deleteItem(String id) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaItem> cinemaItem = cinemaItemRepo.findById(id);

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(!cinemaItem.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Item Not Found");
        }
        cinemaItemRepo.deleteById(id);
        return "Successfully deleted item";
    }

    public String addNewStar(CinemaStar cinemaStar) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaStar> star = cinemaStarRepo.findById(cinemaStar.getName());

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(star.isPresent()){
            throw new BadRequestException("Email address already in use.");
        }
        cinemaStarRepo.save(cinemaStar);
        return "Successfully added star";
    }

    public String editStar(CinemaStar cinemaStar) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaStar> star = cinemaStarRepo.findById(cinemaStar.getName());

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(!star.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Star Not Found");
        }
        cinemaStarRepo.save(cinemaStar);
        return "Successfully edited star";
    }

    public String deleteStar(String id) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<CinemaStar> star = cinemaStarRepo.findById(id);

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(!star.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cinema Star Not Found");
        }
        cinemaStarRepo.deleteById(id);
        return "Successfully deleted star";
    }

    public String addNewEditor(Admin editor) {
        Admin newEditor = adminRepo.findByEmail(editor.getEmail());

        if(newEditor != null){
            throw new BadRequestException("Email address already in use.");
        }
        editor.setPassword(passwordEncoder.encode(editor.getPassword()));
        adminRepo.save(editor);
        return "successfully added editor";
    }

    public String deleteEditor(String id) {
        String adminName = JwtAuthenticationController.getUserFromSession();
        Optional<Admin> editor = Optional.ofNullable(adminRepo.findByEmail(id));

        if(adminName == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Admin User Not Found");
        }
        if(!editor.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Editor Not Found");
        }
        adminRepo.deleteByUsername(id);
        return "Successfully deleted editor";
    }
}

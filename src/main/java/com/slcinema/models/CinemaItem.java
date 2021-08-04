package com.slcinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "CinemaItem")
public class CinemaItem {
    @Id
    private String id;

    @NotNull(message = "category cannot be Null")
    @NotBlank(message = "category name cannot be Blank")
    private String category;

    @NotNull(message = "title cannot be Null")
    @NotBlank(message = "title cannot be Blank")
    private String title;

    @NotNull(message = "image url cannot be Null")
    @NotBlank(message = "image url cannot be Blank")
    private ArrayList<String> imageUrls;

    @NotNull(message = "description cannot be Null")
    @NotBlank(message = "description cannot be Blank")
    private String description;

    @NotNull(message = "cast list cannot be Null")
    @NotBlank(message = "Cast list cannot be Blank")
    private ArrayList<Role> cast;

    @NotNull(message = "director cannot be Null")
    @NotBlank(message = "director cannot be Blank")
    private ArrayList<Role> directors;

    @NotNull(message = "producer cannot be Null")
    @NotBlank(message = "producer cannot be Blank")
    private ArrayList<Role> producers;

    @NotNull(message = "genre cannot be Null")
    @NotBlank(message = "genre cannot be Blank")
    private ArrayList<String> genres;

    private HashMap<String,String> reviews;
    private HashMap<String, Double> rateMap;
    private double ratings;
    private int ratedCount;
    private String youtubeURL;

    public CinemaItem(String id, String category, String title, ArrayList<String> imageUrls, String description, ArrayList<Role> cast, ArrayList<Role> directors, ArrayList<Role> producers, ArrayList<String> genres, HashMap<String, String> reviews, HashMap<String, Double> rateMap, double ratings, int ratedCount, String youtubeURL) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.imageUrls = imageUrls;
        this.description = description;
        this.cast = cast;
        this.directors = directors;
        this.producers = producers;
        this.genres = genres;
        this.reviews = reviews;
        this.rateMap = rateMap;
        this.ratings = ratings;
        this.ratedCount = ratedCount;
        this.youtubeURL = youtubeURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Role> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Role> cast) {
        this.cast = cast;
    }

    public ArrayList<Role> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Role> directors) {
        this.directors = directors;
    }

    public ArrayList<Role> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Role> producers) {
        this.producers = producers;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public HashMap<String, String> getReviews() {
        return reviews;
    }

    public void setReviews(HashMap<String, String> reviews) {
        this.reviews = reviews;
    }

    public HashMap<String, Double> getRateMap() {
        return rateMap;
    }

    public void setRateMap(HashMap<String, Double> rateMap) {
        this.rateMap = rateMap;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public int getRatedCount() {
        return ratedCount;
    }

    public void setRatedCount(int ratedCount) {
        this.ratedCount = ratedCount;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}

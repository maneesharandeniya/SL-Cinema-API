package com.slcinema.models;

public class ReviewObj {
    private String id;
    private String review;

    public ReviewObj(String id, String review) {
        this.id = id;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

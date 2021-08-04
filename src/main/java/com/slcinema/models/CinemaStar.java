package com.slcinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Document("Star")
public class CinemaStar {
    @Id
    private String name;

    @NotNull(message = "description cannot be Null")
    @NotBlank(message = "description cannot be Blank")
    private String description;

    @NotNull(message = "image url cannot be Null")
    @NotBlank(message = "image url cannot be Blank")
    private ArrayList<String> imageUrl;

    public CinemaStar(String name, String description, ArrayList<String> imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
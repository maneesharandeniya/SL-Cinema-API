package com.slcinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document("Star")
public class Star {
    @Id
    private String name;

    @NotNull(message = "description cannot be Null")
    @NotBlank(message = "description cannot be Blank")
    private String description;

    @NotNull(message = "image url cannot be Null")
    @NotBlank(message = "image url cannot be Blank")
    private String imageUrl;

    public Star(String name, String description, String imageUrl) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

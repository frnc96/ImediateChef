package com.example.frens.secondchefv2.models;

import com.example.frens.secondchefv2.services.DataService;

public class Pictures {
    private String caption;
    private String image;

    public Pictures(String caption, String image) {
        this.caption = caption;
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public String getImage() {
        return DataService.getInstance().IMAGES_URL + image;
    }
}

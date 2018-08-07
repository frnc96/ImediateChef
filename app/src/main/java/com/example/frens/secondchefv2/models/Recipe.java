package com.example.frens.secondchefv2.models;

import com.example.frens.secondchefv2.services.DataService;

import java.util.ArrayList;

public class Recipe {
    private String image;
    private String name;
    private String description;
    private ArrayList<Children> children;
    private ArrayList<Pictures> pictures;

    public Recipe(String image, String name, String description, ArrayList<Children> children, ArrayList<Pictures> pictures) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.children = children;
        this.pictures = pictures;
    }

    public String getImage() {
        return DataService.getInstance().IMAGES_URL + image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public ArrayList<Pictures> getPictures() {
        return pictures;
    }
}

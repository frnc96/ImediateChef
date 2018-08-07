package com.example.frens.secondchefv2.models;

import java.util.ArrayList;

public class Data {
    private ArrayList<Recipe> recipes;

    public Data(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}

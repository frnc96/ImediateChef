package com.example.frens.secondchefv2.models;

import java.util.ArrayList;

public class Children {
    private ArrayList<Ingredients> ingredients;

    public Children(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }
}

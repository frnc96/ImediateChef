package com.example.frens.secondchefv2.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.frens.secondchefv2.fragments.RecipeDetailsFragment;
import com.example.frens.secondchefv2.fragments.RecipeDetailsPagerFragment;
import com.example.frens.secondchefv2.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsPagerAdapter extends FragmentStatePagerAdapter {

    String jsonRecipe;
    List<Fragment> pages = new ArrayList<>();

    public RecipeDetailsPagerAdapter(FragmentManager fm, String jsonRecipe) {
        super(fm);
        this.jsonRecipe = jsonRecipe;
    }

    /**
     * identifies which fragment is being inserted and gives
     * the respective parameters
     * @param i
     * @return the fragment which will store the details of the recipe
     */
    @Override
    public Fragment getItem(int i) {
        Fragment recipeDetails = pages.get(i);
        Bundle args = new Bundle();

        args.putString(RecipeDetailsFragment.ARG_JSON, jsonRecipe);

        switch(i){
            case 1:
                args.putBoolean(RecipeDetailsFragment.ARG_IS_INGREDIENT_PAGE, true);
                break;
            default:
                args.putBoolean(RecipeDetailsFragment.ARG_IS_INGREDIENT_PAGE, false);
                break;
        }

        recipeDetails.setArguments(args);
        return recipeDetails;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    /**
     * it takes as parameter the fragment that will be added to the pager
     * @param f
     */
    public void addDetailsPage(Fragment f){
        pages.add(f);
    }
}

package com.example.frens.secondchefv2.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.example.frens.secondchefv2.adapters.RecipeDetailsPagerAdapter;
import com.example.frens.secondchefv2.models.Recipe;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailsFragment extends Fragment implements View.OnClickListener{

    Toolbar toolbar;
    public static final String ARG_JSON = "jsonStringOfRecipe";
    public static final String ARG_IS_INGREDIENT_PAGE= "inTheCaseWeHaveIgredientPage";

    Recipe recipe;
    Button preparazzioneBtn;
    Button ingredientiBtn;
    ViewPager detailsPager;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment RecipeDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetailsFragment newInstance(String recipeJson) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(RecipeDetailsFragment.ARG_JSON, recipeJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        toolbar = v.findViewById(R.id.prices_details_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Recipe Title"); //Load the fragments toolbar

        detailsPager = v.findViewById(R.id.recipeDetailsPager);
        ImageView recipeImg = v.findViewById(R.id.recipeImg);
        preparazzioneBtn = v.findViewById(R.id.preparazzioneBtn);
        ingredientiBtn = v.findViewById(R.id.ingredientiBtn);
        String jsonRecipe = getArguments().getString(ARG_JSON);
        recipe = new Gson().fromJson(jsonRecipe, Recipe.class);

        /**
         * creating the adapter for the pager and adding the fragments
         */
        RecipeDetailsPagerAdapter adaptor = new RecipeDetailsPagerAdapter(getActivity().getSupportFragmentManager(), jsonRecipe);
        adaptor.addDetailsPage(RecipeDetailsPagerFragment.newInstance());
        adaptor.addDetailsPage(RecipeDetailsPagerFragment.newInstance());
        detailsPager.setAdapter(adaptor);

        /**
         * loads the thumbnail image of the recipe on the new page
         */
        Picasso.get().load(recipe.getImage()).into(recipeImg);
        recipeImg.setBackgroundColor(Color.WHITE);

        recipeImg.setOnTouchListener(new View.OnTouchListener() {
            /**
             * override the touch method so we do not click through the image
             * @param view
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        preparazzioneBtn.setOnClickListener(this);
        ingredientiBtn.setOnClickListener(this);

        detailsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * unselects all the buttons and selects the one which
             * corresponds to the page that we navigated to
             * @param i
             */
            @Override
            public void onPageSelected(int i) {
                resetButtonColor();

                switch (i){
                    case 0:
                        preparazzioneBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));
                        break;
                    case 1:
                        ingredientiBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return v;
    }

    /**
     * navigate to the respective page on tab button click
     * @param view
     */
    @Override
    public void onClick(View view) {
        resetButtonColor();
        view.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));

        switch(view.getId()){
            case R.id.preparazzioneBtn:
                detailsPager.setCurrentItem(0);
                break;
            case R.id.ingredientiBtn:
                detailsPager.setCurrentItem(1);
                break;
        }
    }

    /**
     * Unselect the pager navigation buttons
     */
    private void resetButtonColor(){
        preparazzioneBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_selected));
        ingredientiBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_selected));
    }
}

package com.example.frens.secondchefv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.example.frens.secondchefv2.adapters.RecipeAdapter;
import com.example.frens.secondchefv2.models.Recipe;
import com.example.frens.secondchefv2.services.DataService;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainRecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainRecipesFragment extends Fragment {

    Toolbar toolbar;
    static MainRecipesFragment mainRecipesFragment;

    public static MainRecipesFragment getMainRecipesFragment() {
        return mainRecipesFragment;
    }

    private static void setMainRecipesFragment(MainRecipesFragment mainRecipesFragment) {
        MainRecipesFragment.mainRecipesFragment = mainRecipesFragment;
    }

    public MainRecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment MainRecipesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainRecipesFragment newInstance() {
        MainRecipesFragment fragment = new MainRecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainRecipesFragment(this);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_recipes, container, false);

        toolbar = v.findViewById(R.id.prices_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Ricette della settimana"); //Load the fragments toolbar
        ProgressBar spinner = v.findViewById(R.id.spinner);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_recipes);

        recyclerView.setHasFixedSize(true);

        RecipeAdapter adapter = new RecipeAdapter(DataService.getInstance().getRecipes(recyclerView, getContext(), spinner), getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

    /**
     * Recieves the recipe of the card that the user clicked on
     * and loads the detail page
     * @param recipe
     */
    public void loadDetails(Recipe recipe){
        String recipeJson = new Gson().toJson(recipe);
        RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeJson);

        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.recipe_details_container, recipeDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

}

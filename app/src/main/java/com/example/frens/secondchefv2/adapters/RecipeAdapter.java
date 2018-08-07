package com.example.frens.secondchefv2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.fragments.MainRecipesFragment;
import com.example.frens.secondchefv2.holders.RecipeViewHolder;
import com.example.frens.secondchefv2.models.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context context;

    /**
     * Initializes the recipe list and provides the adapter with the context of the fragment
     * @param recipes
     * @param context
     */
    public RecipeAdapter(ArrayList<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    /**
     * when the view holder is bound whe update the UI with the downloaded information
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, final int position) {

        final Recipe recipe = recipes.get(position);
        holder.updateUI(recipe);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //triggers when we click the recipe
                MainRecipesFragment.getMainRecipesFragment().loadDetails(recipe);
            }
        });

    }

    /**
     *
     * @return number of recipes in the list
     */
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    /**
     * we inflate the view (the card)
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe,parent, false);
        return new RecipeViewHolder(recipeCard);
    }
}

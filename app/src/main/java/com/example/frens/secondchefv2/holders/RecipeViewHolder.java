package com.example.frens.secondchefv2.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.models.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private ImageView mainImage;
    private TextView titleTextView;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        this.mainImage = itemView.findViewById(R.id.main_image);
        this.titleTextView = itemView.findViewById(R.id.main_text);
    }

    public void updateUI(Recipe recipe){
        String uri = recipe.getImage();
        titleTextView.setText(recipe.getName());

        Picasso.get()
                .load(uri)
                .placeholder(R.drawable.plate)
                .into(mainImage);
    }

}

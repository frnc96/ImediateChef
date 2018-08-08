package com.example.frens.secondchefv2.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.fragments.RecipeDetailsFragment;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        String recipeJson = "";
        if(b != null)
            recipeJson = b.getString("JSON");

        RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeJson);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main2activity_container, recipeDetailsFragment)
                .commit();

    }

}

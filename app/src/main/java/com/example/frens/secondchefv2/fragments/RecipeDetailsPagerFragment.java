package com.example.frens.secondchefv2.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.models.Ingredients;
import com.example.frens.secondchefv2.models.Recipe;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDetailsPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailsPagerFragment extends Fragment {

    public RecipeDetailsPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment RecipeDetailsPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetailsPagerFragment newInstance() {
        RecipeDetailsPagerFragment fragment = new RecipeDetailsPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * in this method after checking which fragment it is we
     * create the appropriate views and display the information accordingly
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_details_pager, container, false);

        TextView title = v.findViewById(R.id.recipePagerTitle);
        LinearLayout holder = v.findViewById(R.id.details_layout);

        Recipe recipe = new Gson().fromJson(getArguments().getString(RecipeDetailsFragment.ARG_JSON), Recipe.class);

        title.setText(Html.fromHtml(recipe.getName()));

        /**
         * if it is not the ingredient page just add a textview and display the description
         */
        if(!getArguments().getBoolean(RecipeDetailsFragment.ARG_IS_INGREDIENT_PAGE)){
            ScrollView scrollView = new ScrollView(getContext());
            TextView desc = new TextView(getContext());
            desc.setText(Html.fromHtml(recipe.getDescription()));
            desc.setTextColor(Color.BLACK);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(30,30,30,30);

            scrollView.setLayoutParams(params);
            scrollView.addView(desc);
            holder.addView(scrollView);
        }
        /**
         * if it is the ingredient page build the ingredients table
         */
        else{
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getContext());
            TableLayout table = new TableLayout(getContext());

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            param.setMargins(30,30,30,30);

            horizontalScrollView.setLayoutParams(param);

            TableRow rows = new TableRow(getContext());

            TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            p.setMarginEnd(20);

            TextView t = new TextView(getContext());
            t.setText("INGREDIENTI");
            t.setTextColor(Color.BLACK);
            t.setTypeface(null, Typeface.BOLD);
            t.setLayoutParams(p);
            rows.addView(t);

            t = new TextView(getContext());
            t.setText("2 PERSONE");
            t.setTextColor(Color.BLACK);
            t.setTypeface(null, Typeface.BOLD);
            t.setLayoutParams(p);
            rows.addView(t);

            t = new TextView(getContext());
            t.setText("4 PERSONE");
            t.setTextColor(Color.BLACK);
            t.setTypeface(null, Typeface.BOLD);
            t.setLayoutParams(p);
            rows.addView(t);

            t = new TextView(getContext());
            t.setText("6 PERSONE");
            t.setTextColor(Color.BLACK);
            t.setTypeface(null, Typeface.BOLD);
            t.setLayoutParams(p);
            rows.addView(t);

            table.addView(rows);

            for(Ingredients ingredient : recipe.getChildren().get(0).getIngredients()){
                rows = new TableRow(getContext());

                double baseAmmount = 20.00;
                double ammount = 0;

                t = new TextView(getContext());
                t.setText(ingredient.getName());
                t.setTextColor(Color.BLACK);
                t.setLayoutParams(p);
                rows.addView(t);

                t = new TextView(getContext());
                t.setText(Double.toString(ammount+=baseAmmount)+"g");
                t.setTextColor(Color.BLACK);
                t.setLayoutParams(p);
                rows.addView(t);

                t = new TextView(getContext());
                t.setText(Double.toString(ammount+=baseAmmount)+"g");
                t.setTextColor(Color.BLACK);
                t.setLayoutParams(p);
                rows.addView(t);

                t = new TextView(getContext());
                t.setText(Double.toString(ammount+=baseAmmount)+"g");
                t.setTextColor(Color.BLACK);
                t.setLayoutParams(p);
                rows.addView(t);

                table.addView(rows);
            }
            horizontalScrollView.addView(table);
            holder.addView(horizontalScrollView);
        }

        return v;
    }

}

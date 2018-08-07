package com.example.frens.secondchefv2.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.frens.secondchefv2.adapters.RecipeAdapter;
import com.example.frens.secondchefv2.interfaces.DownloadRecipesApi;
import com.example.frens.secondchefv2.models.Data;
import com.example.frens.secondchefv2.models.Recipe;
import com.example.frens.secondchefv2.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {
    private static final DataService ourInstance = new DataService();

    RecyclerView recyclerViewS;
    public Context contextS;
    ProgressBar spinnerS;

    public final String IMAGES_URL = "https://www.secondchef.it/images/thumb300_";

    ArrayList<Recipe> recipes = new ArrayList<>();

    public static DataService getInstance() {return ourInstance;}

    private DataService() {
    }

    /**
     * the data are downloaded, they are inserted in the recycler
     * view cards and when this task is performing we display a loading bar
     * @param recyclerView
     * @param context
     * @param spinner
     * @return the list of recipes
     */
    public ArrayList<Recipe> getRecipes(RecyclerView recyclerView, Context context, ProgressBar spinner){

        recyclerViewS = recyclerView;
        contextS = context;
        spinnerS = spinner;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DownloadRecipesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DownloadRecipesApi api = retrofit.create(DownloadRecipesApi.class);

        Call<Data> call = api.getData();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                recipes = data.getRecipes();

                RecipeAdapter adapter = new RecipeAdapter(recipes, contextS);
                recyclerViewS.setAdapter(adapter);

                spinnerS.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("Jari", "Coul not get JSON: "+ t.getLocalizedMessage());
            }
        });

        return recipes;
    }

    /**
     * it takes 2 parameters which are submitted to the server API and gets the response
     * the user is notified through toasts
     * @param email
     * @param password
     */
    public void logIn(String email, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DownloadRecipesApi.BASE_LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DownloadRecipesApi api = retrofit.create(DownloadRecipesApi.class);

        Call<User> call = api.logInWithCredencials(email, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                        User user = response.body();

                        SharedPreferences sp = contextS.getSharedPreferences("myPrefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("user_token", user.getToken());
                        editor.commit();

                        Toast.makeText(contextS, "Login successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contextS, "Wrong email or password", Toast.LENGTH_LONG).show();
                    Log.e("Jari", "Raw response: "+ response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Jari", "Can't get response from server: "+ t.getLocalizedMessage());
            }
        });
    }

}

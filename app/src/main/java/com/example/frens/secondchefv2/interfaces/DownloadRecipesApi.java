package com.example.frens.secondchefv2.interfaces;

import com.example.frens.secondchefv2.models.Data;
import com.example.frens.secondchefv2.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DownloadRecipesApi {

    String BASE_URL = "https://www.secondchef.it/api/v2/";
    String BASE_LOGIN_URL = "https://beta.secondchef.it/api/v2/";

    @GET("recipe/choose/1")
    Call<Data> getData();

    @FormUrlEncoded
    @POST("login")
    Call<User> logInWithCredencials(@Field("email") String email, @Field("password") String password);

}

package com.example.mushfiq.practiceprojects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Retro {

    String Base_URL = "https://www.simplifiedcoding.net/demos/";

    @POST("marvel")
    Call<List<Hero>> getHeros ();


}

package com.example.fragmenttask.ui.java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    static final String BASE_URL = "https://api.androidhive.info/json/";

    @GET("inbox.json")
    Call<List<JavaPOJO>> getJavaPojo();
}

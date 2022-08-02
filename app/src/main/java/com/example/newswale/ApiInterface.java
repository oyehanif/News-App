package com.example.newswale;

import com.example.newswale.Modals.mainNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    public static final String BASE_URL = "https://newsapi.org/v2/";


    @GET("top-headlines")
    Call<mainNews> getAllNews(
        @Query("country") String country,
        @Query("pageSize") int pageSize,
        @Query("apiKey") String apiKey
    );


    @GET("top-headlines")
    Call<mainNews> getcategory(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );

    @GET("everything/{q}")
    Call<mainNews> getSearchNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );
}

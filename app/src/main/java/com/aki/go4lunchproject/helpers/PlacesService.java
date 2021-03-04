package com.aki.go4lunchproject.helpers;

import com.aki.go4lunchproject.models.Restaurant;
import com.aki.go4lunchproject.models.Result;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesService {

    public static PlacesService setRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PlacesService.class);
    }

    @GET("/maps/api/place/nearbysearch/json")
    Call<JsonObject> getRestaurantsAround(@Query("key") String apiKey,
                                         @Query("location") String userCoordinates,
                                         @Query("type") String type,
                                         @Query("rankby") String rankBy);
}

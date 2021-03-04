package com.aki.go4lunchproject.helpers;

import android.util.Log;

import androidx.annotation.Nullable;

import com.aki.go4lunchproject.BuildConfig;
import com.aki.go4lunchproject.models.Restaurant;

import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class RestaurantCalls {

    public interface Callbacks {
        void onResponse(@Nullable List<Restaurant> restaurants);
        void onFailure();
        void onResponse(@Nullable Restaurant restaurant);
    }

    public static void fetchRestaurantsAround(Callbacks callbacks, String coordinates){

        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        PlacesService placesService = PlacesService.setRetrofit();

        Call<List<Restaurant>> call = placesService.getRestaurantsAround(BuildConfig.API_KEY, coordinates, "restaurant", "distance");

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

                if(callbacksWeakReference.get() != null) {
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                Log.d(TAG, "onFailure: "+ t.getMessage());

                if(callbacksWeakReference.get() != null) {
                    callbacksWeakReference.get().onFailure();
                }
            }
        });

    }

    public static void fetchRestaurantsTest(Callbacks callbacks, String coordinates){

        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        PlacesService placesService = PlacesService.setRetrofit();

        Call<Restaurant> call = placesService.getRestaurantAroundTest(BuildConfig.API_KEY, coordinates, "restaurant", "distance");

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                if(callbacksWeakReference.get() != null) {
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

                Log.d(TAG, "onFailure: "+ t.getMessage());

                if(callbacksWeakReference.get() != null) {
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}

package com.aki.go4lunchproject.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aki.go4lunchproject.models.Restaurant;

import java.util.ArrayList;

public class RestaurantViewModel extends ViewModel {

    MutableLiveData<ArrayList<Restaurant>> restaurantLiveData;
    ArrayList<Restaurant> restaurantArrayList;

    public RestaurantViewModel() {
        restaurantLiveData = new MutableLiveData<>();

        //TODO : calling the API in the init method
        init();
    }

    public MutableLiveData<ArrayList<Restaurant>> getRestaurantLiveData() {
        return restaurantLiveData;
    }

    private void init() {
//        populateList();
        restaurantLiveData.setValue(restaurantArrayList);
    }

 /**   public void populateList() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Le Sakura");
        restaurant.setAddress("42 rue de l'Univers");
        restaurant.setBookedState(true);
        restaurant.setUid("123");

        restaurantArrayList = new ArrayList<>();
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
        restaurantArrayList.add(restaurant);
    }**/
}

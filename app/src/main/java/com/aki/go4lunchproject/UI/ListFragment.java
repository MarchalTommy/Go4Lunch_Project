package com.aki.go4lunchproject.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.FragmentListBinding;
import com.aki.go4lunchproject.helpers.RestaurantCalls;
import com.aki.go4lunchproject.models.Restaurant;
import com.aki.go4lunchproject.models.Result;
import com.aki.go4lunchproject.viewModels.RestaurantViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment implements LifecycleOwner, RestaurantCalls.Callbacks {

    RestaurantViewModel viewModel;
    RestaurantListAdapter adapter;

    FragmentListBinding bindings;

    //List of restaurants fetched from the API call, used to populate the RV
    List<Result> restaurantsAround = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: FRAGMENT CREATED");
        super.onViewCreated(view, savedInstanceState);
        bindings = FragmentListBinding.bind(view);


        bindings.progressBar.show();

        adapter = new RestaurantListAdapter();

        bindings.restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        bindings.restaurantsRecyclerView.setAdapter(adapter);

        executeHttpRequestWithRetrofit();
    }

    private void executeHttpRequestWithRetrofit() {
        Log.d(TAG, "executeHttpRequestWithRetrofit: FETCHING DATAS FROM API");
        RestaurantCalls.fetchRestaurantsAround(this, "48.865623,2.347057");
    }

    @Override
    public void onResponse(@Nullable JsonObject jsonObject) {
        bindings.noData.setVisibility(View.GONE);
        if (jsonObject != null) {
            restaurantsAround.clear();
            Log.d(TAG, "onResponse: RESTAURANTS HAS BEEN FOUNDS" + jsonObject.toString());

            Gson gson = new Gson();
            Restaurant restaurant;
            restaurant = gson.fromJson(jsonObject, Restaurant.class);
            Log.d(TAG, "onResponse: " + restaurant.getResults().get(0).getName());

            for (Result r : restaurant.getResults()) {
                if(r.getTypes().get(0).equals("restaurant")){
                    restaurantsAround.add(r);
                }
            }

            adapter.updateList(restaurantsAround);

            if (restaurantsAround.isEmpty()) {
                bindings.noData.setText("It appears to be a problem here...\nPlease come again later !");
            } else {
                bindings.noData.setVisibility(View.GONE);
            }
            bindings.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure() {
        Log.d(TAG, "onFailure: RESTAURANTS NOT FOUNDS");

        bindings.noData.setVisibility(View.VISIBLE);
        bindings.progressBar.setVisibility(View.GONE);
        bindings.noData.setText("An error occured. Please retry later.");
    }
}
